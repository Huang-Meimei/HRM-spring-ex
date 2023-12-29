package com.mole.springbootexample.controller.user;

import com.mole.springbootexample.model.RequestCodeData;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/code/")
public class CodeController {

    @PostMapping("getSms")
    public String getCode (
            @RequestBody RequestCodeData requestCodeData){
        System.out.println(requestCodeData);
        if (requestCodeData.module().equals("login")){
            return "111111";
        };
        //User user = userService.getUserByEmail(userLogInRequest);

        return null;
    }
}