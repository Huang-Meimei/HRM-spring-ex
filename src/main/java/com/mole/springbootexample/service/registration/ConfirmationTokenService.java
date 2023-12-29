package com.mole.springbootexample.service.registration;

import com.mole.springbootexample.model.token.ConfirmationToken;
import com.mole.springbootexample.model.user.User;
import com.mole.springbootexample.service.dao.imp.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
   // private final AuthenticationManager authenticationManager;

    public void saveConfirmationToken(ConfirmationToken confirmationToken){
        confirmationTokenRepository.save(confirmationToken);
    }


    public String extractUsername(String token){
        ConfirmationToken confirmationToken = getToken(token)
                .orElseThrow(()->new IllegalStateException("token not found!"));
        return confirmationToken.getUser().getEmail();
    }

    public Optional<ConfirmationToken> getToken(String token){
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmAt(String token){
        ConfirmationToken confirmationToken = getToken(token).get();
        User user = confirmationToken.getUser();
        confirmationToken.setConfirmedAt(LocalDateTime.now());
        confirmationTokenRepository.save(confirmationToken);
    }
}
