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
    public String handleCredentialsUploadAndEdit(Model model, @ModelAttribute("credentials") Credentials creds, Authentication authentication ){
        User user = this.userService.getUser(authentication.getName());
        Long userId = user.getUserId();
        System.out.println(creds.getCredentialId());
        try{
            if(creds.getCredentialId() == 0) {
                this.credentialService.insertCreds(creds,userId);
                System.out.println("Inserting"+creds.getCredentialId());
                model.addAttribute("success",true);
                model.addAttribute("message","New Credentails are  added successfully!");
                System.out.println("Creds added");
            }else{
                this.credentialService.updateCreds(creds, userId);
                model.addAttribute("success", true);
                model.addAttribute("message", "Credential updated successfully");
                System.out.println("Creds updated");
            }

        }catch (Exception e){
//            System.out.println(e.printStackTrace());
            e.printStackTrace();
            model.addAttribute("error",true);
            model.addAttribute("message",e.getMessage());
        }
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
