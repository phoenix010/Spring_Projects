package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class CredentialsController {
    private CredentialService credentialService;
    private UserService userService;

    public CredentialsController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping("/credentials")
    public String handleCredentailUpload(Model model, @ModelAttribute("credentials") Credentials creds, Authentication authentication ){
        User user = this.userService.getUser(authentication.getName());
        Long userId = user.getUserId();
        try{
            this.credentialService.insert(creds, userId);
            System.out.println("Credentials added");
//            System.out.println(this.credentialService.getCredIdByUserId(userId));
//            System.out.println(this.credentialService.getusernameByUserId(userId));
            model.addAttribute("success",true);
            model.addAttribute("message","New Credentails are added successfully!");
        }catch(Exception e){
            model.addAttribute("error",true);
            model.addAttribute("message",e.getMessage());
        }
        System.out.println("Crdentials service is working");
        return "result";
    }

    @GetMapping("/deleteCreds/{credentialId}")
    public String deleteCreds(@PathVariable("credentialId") long credentialId, Authentication authentication, Model model) {
        User user = this.userService.getUser(authentication.getName());
        long userId = user.getUserId();
//        System.out.println("credId is in the deleteNote function is "+noteId);

        try {
            this.credentialService.deleteCreds(credentialId, userId);
            model.addAttribute("success",true);
            model.addAttribute("message", "Note deleted!");
        } catch (Exception e) {
            model.addAttribute("error",true);
            model.addAttribute("message","System error!" + e.getMessage());
        }
        return "result";
    }



}
