package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.LoginForm;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller()
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String loginView(@ModelAttribute("loginForm") LoginForm loginForm) {
//        System.out.println(isError);
        System.out.println("Inside Get Login controller");
        return "login";
    }
//    @PostMapping
//    public String loginToHome(@ModelAttribute("loginForm") LoginForm loginForm) {
////        System.out.println(isError);
//        System.out.println("Inside POST Login controller");
//        return "home";
//    }


}