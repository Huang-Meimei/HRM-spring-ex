package com.mole.springbootexample.service;

import com.mole.springbootexample.service.dao.imp.UserRepository;
import com.mole.springbootexample.model.user.User;
import com.mole.springbootexample.model.user.UserLogInRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByEmail(UserLogInRequest userLogInRequest){
        return new User();
    }
}
