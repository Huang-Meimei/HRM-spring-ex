package com.mole.springbootexample.service.registration;

import com.mole.springbootexample.model.token.ConfirmationToken;
import com.mole.springbootexample.service.dao.imp.UserRepository;
import com.mole.springbootexample.model.user.AppUserRole;
import com.mole.springbootexample.model.user.User;
import com.mole.springbootexample.model.user.UserRegisterRequest;
import com.mole.springbootexample.security.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RegistrationService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG="user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    //private final AuthenticationManager authenticationManager;

    public String register(UserRegisterRequest userRegisterRequest) throws IllegalStateException{
        boolean isValidEmail = emailValidator.test(userRegisterRequest.email());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        return signUpUser(
                new User(userRegisterRequest.username(), userRegisterRequest.email(), userRegisterRequest.password(), AppUserRole.USER)
        );
    }

    private String signUpUser(User user){
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if (userExists) {
            throw new IllegalStateException("email already taken");
        }
        String encodedPw = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPw);
        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token,LocalDateTime.now(),LocalDateTime.now().plusMinutes(15),user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return "It works: token is "+token;
    }

    @Transactional
    public String confirmToken(String token){
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(()->{
                    return new IllegalStateException("token not found!");});
        if (confirmationToken.getConfirmedAt()!=null){
            throw new IllegalStateException("email already confirmed");
        }
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token expired");
        }
        confirmationTokenService.setConfirmAt(token);
        enableUser(confirmationToken.getUser().getEmail());

        return "confirmed";

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException(USER_NOT_FOUND_MSG.formatted(email)));


        return user;
    }

    public void enableUser(String email){
        User user = userRepository.findByEmail(email).get();
        user.setEnabled(true);
        userRepository.save(user);
    }


}
