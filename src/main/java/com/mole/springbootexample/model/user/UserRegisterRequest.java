package com.mole.springbootexample.model.user;

public record UserRegisterRequest(
        String email,
        String password,

        String username
) {
}
