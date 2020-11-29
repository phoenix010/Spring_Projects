package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.LoginForm;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService){
        this.userService = userService;
    }
    @GetMapping()
    public String loginView() {
        return "login";
    }

    @PostMapping
    public String loginUser(LoginForm loginForm, Model model){
//        String loginerror = null;

        if(userService.validateUser(loginForm)){
            System.out.println("Login successful");
            return "home";
        }else{
            model.addAttribute("loginError","INVALID USERNAME OR PASSWORD");
        }
        //check the username and password submitted in the form with the database.
        // If there are no errors go to home page otherwise display errors
        return"login";
    }
}

