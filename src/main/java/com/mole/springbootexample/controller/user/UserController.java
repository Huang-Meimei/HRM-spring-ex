package com.mole.springbootexample.controller.user;

import com.mole.springbootexample.model.DataResponse;
import com.mole.springbootexample.model.user.User;
import com.mole.springbootexample.model.user.UserLogInRequest;
import com.mole.springbootexample.model.user.UserRegisterRequest;
import com.mole.springbootexample.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/users/")
@AllArgsConstructor
public class UserController {

    private final UserService userService;



    @GetMapping
    public List<User> getUsers(){
        return List.of(new User());
    }



    @PostMapping("login")
    public User logInUser (
            @RequestBody UserLogInRequest userLogInRequest){
        System.out.println("11111");
        //User user = userService.getUserByEmail(userLogInRequest);

        return null;
    }

    @PostMapping("register")
    public DataResponse registerUser (
            @RequestBody UserRegisterRequest userRegisterRequest){
        //User user = userService.getUserByEmail(userLogInRequest);
        User user = new User();

        return new DataResponse(user,0);
    }

}
