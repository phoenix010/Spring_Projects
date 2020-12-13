package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.LoginForm;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String loginView(@ModelAttribute("loginForm") LoginForm loginForm, Model model,@ModelAttribute("confirmation") final Object confirmation ) {
        if(confirmation.equals(true)){
            model.addAttribute("confirmation",true);
            System.out.println("confirmation is true");
            return "login";
        }
        model.addAttribute("confirmation",false);
        System.out.println("confirmation is false");
        System.out.println("Inside Get Login controller");
        return "login";
    }
}