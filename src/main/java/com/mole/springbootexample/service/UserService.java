package com.mole.springbootexample.service;

import com.mole.springbootexample.model.user.User;
import com.mole.springbootexample.model.user.UserLogInRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getUserByEmail(UserLogInRequest userLogInRequest){
        return new User(userLogInRequest.email(),userLogInRequest.password());
    }

}
