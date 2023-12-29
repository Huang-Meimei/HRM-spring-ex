package com.mole.springbootexample.controller.user;

import com.mole.springbootexample.model.user.UserRegisterRequest;
import com.mole.springbootexample.service.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/register")
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity register (
            @RequestBody UserRegisterRequest userRegisterRequest){
        return ResponseEntity.accepted().body(registrationService.register(userRegisterRequest));
    }

    @GetMapping("/confirm")
    public ResponseEntity confirm (
            @RequestParam("token") String token){
        return ResponseEntity.accepted().body(registrationService.confirmToken(token));


    }
}
