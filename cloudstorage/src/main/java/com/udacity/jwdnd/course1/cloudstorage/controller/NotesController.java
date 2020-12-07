package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NotesController {
    private NoteService noteService;
    private UserService userService;

    public NotesController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/notes")
    public String handleNoteUploadAndEdit(Model model, @ModelAttribute("noteForm") Note note, Authentication authentication ){
        User user = this.userService.getUser(authentication.getName());
        Long userId = user.getUserId();
        try{
            this.noteService.inserOrUpdatetNote(note,userId);
            System.out.println(this.noteService.getNoteTitle(note));
            model.addAttribute("success",true);
            model.addAttribute("message","New Note added successfully!");
            System.out.println("Note added");
        }catch(Exception e){
            model.addAttribute("error",true);
            model.addAttribute("message",e.getMessage());
        }
        return "result";
    }

    @GetMapping("/deleteNote/{noteId}")
    public String deleteNote(@PathVariable("noteId") long noteId, Authentication authentication, Model model) {
        User user = this.userService.getUser(authentication.getName());
        long userId = user.getUserId();
        System.out.println("NoteId is in the deleteNote function is "+noteId);

        try {
            noteService.deleteNote(noteId, userId);
            model.addAttribute("success",true);
            model.addAttribute("message", "Note deleted!");
        } catch (Exception e) {
            model.addAttribute("error",true);
            model.addAttribute("message","System error!" + e.getMessage());
        }
        return "result";
    }


}
