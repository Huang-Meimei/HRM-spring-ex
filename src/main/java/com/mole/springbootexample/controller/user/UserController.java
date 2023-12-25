package com.mole.springbootexample.controller.user;

import com.mole.springbootexample.customer.Customer;
import com.mole.springbootexample.model.user.User;
import com.mole.springbootexample.model.user.UserLogInRequest;
import com.mole.springbootexample.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/users/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<User> getUsers(){
        return List.of(new User("111@qq.com","111111a"));
    }



    @PostMapping("login")
    public User logInUser (
            @RequestBody UserLogInRequest userLogInRequest){
        System.out.println("11111");
        //User user = userService.getUserByEmail(userLogInRequest);

        return null;
    }
}
