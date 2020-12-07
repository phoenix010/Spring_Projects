package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
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



    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;

    @Autowired
    private NoteService noteService;

    @GetMapping
    public String homeView(Model model, Authentication authentication) {
    User user=userService.getUser(authentication.getName());
    List<File> file=this.fileService.filesUpload(user.getUserId());
    model.addAttribute("files",file);
    System.out.println("Inside Home controller-GetMapping");
    List<Note> notes=this.noteService.noteUpload(user.getUserId());
    model.addAttribute("notes",notes);
    model.addAttribute("noteForm", new Note());
    model.addAttribute("credentials", new Credentials());
    return "home";
    }
}
