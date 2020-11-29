package com.udacity.jwdnd.course1.cloudstorage.model;

public class LoginForm {
    private String username;
    private String password;
    private Boolean loginError;


    public Boolean getLoginError() {
        return loginError;
    }

    public void setLoginError(Boolean loginError) {
        this.loginError = loginError;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
