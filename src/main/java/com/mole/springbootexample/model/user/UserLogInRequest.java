package com.mole.springbootexample.model.user;

public record UserLogInRequest (
        String email,


        String password
)
{
}
