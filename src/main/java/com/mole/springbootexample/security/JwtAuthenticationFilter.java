package com.mole.springbootexample.security;

import com.mole.springbootexample.model.token.ConfirmationToken;
import com.mole.springbootexample.model.user.User;
import com.mole.springbootexample.service.UserService;
import com.mole.springbootexample.service.registration.ConfirmationTokenService;
import com.mole.springbootexample.service.registration.RegistrationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final ConfirmationTokenService confirmationTokenService;
    private final RegistrationService registrationService;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null || !authHeader.startsWith("token=")){
            filterChain.doFilter(request,response);
            System.out.println("not authorization header");
            return;
        }
        jwt = authHeader.substring(6);
        ConfirmationToken token = confirmationTokenService.getToken(jwt)
                .orElseThrow(()->new IllegalStateException("Token not found!"));
        User user = token.getUser();
        //System.out.println(user);
        if (user!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            registrationService.loadUserByUsername(user.getEmail());
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
            );
            authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);

        }
        //System.out.println(SecurityContextHolder.getContext());
        filterChain.doFilter(request,response);
    }
}
