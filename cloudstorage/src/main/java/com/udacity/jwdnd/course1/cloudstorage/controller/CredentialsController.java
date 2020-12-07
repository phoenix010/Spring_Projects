package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class CredentialsController {
    private CredentialService credentialService;
    private UserService userService;

    public CredentialsController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping("/credentails")
    public String handleCredentailUpload(Model model, @ModelAttribute("creadentails")Credentials creds, Authentication authentication ){
        User user = this.userService.getUser(authentication.getName());
        Long userId = user.getUserId();
        try{
            this.credentialService.insert(creds, userId);
            model.addAttribute("success",true);
            model.addAttribute("message","New Credentails are added successfully!");
        }catch(Exception e){
            model.addAttribute("error",true);
            model.addAttribute("message",e.getMessage());
        }
        return "result";
    }




}
