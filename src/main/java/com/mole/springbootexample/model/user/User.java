package com.mole.springbootexample.model.user;

public class User {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String email;
    private String password;

    public User(){

    }

    public User(String email, String password){
        this.email = email;
        this.id = 7;
        this.password = password;
    }

}
