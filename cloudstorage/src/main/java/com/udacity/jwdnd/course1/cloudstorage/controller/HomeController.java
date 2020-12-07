package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
     private UserService userService;
     private FileService fileService;
    private NoteService noteService;
    private CredentialService credentialService;

    public HomeController(UserService userService, FileService fileService, NoteService noteService, CredentialService credentialService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping
    public String homeView(Model model, Authentication authentication) {
    User user=userService.getUser(authentication.getName());
    List<File> file=this.fileService.filesUpload(user.getUserId());
    model.addAttribute("files",file);
    System.out.println("Inside Home controller-GetMapping");
    List<Note> notes=this.noteService.noteUpload(user.getUserId());
    model.addAttribute("notes",notes);
    model.addAttribute("noteForm", new Note());
    List <Credentials> creds = this.credentialService.credsUpload(user.getUserId());
    model.addAttribute("creds",creds);
    model.addAttribute("credentials", new Credentials());
    return "home";
    }
}
