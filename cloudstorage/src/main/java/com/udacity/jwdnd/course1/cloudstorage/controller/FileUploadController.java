package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller

public class FileUploadController {

    private final FileService fileService;
    private UserService userService;


    public FileUploadController(FileService fileService, UserService userService) {

        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/file-upload")
    public String handleFileUpload(@RequestParam("fileUpload") MultipartFile fileUpload, Model model, Authentication authentication){
            if(fileUpload.isEmpty()){
                model.addAttribute("error", true);
                model.addAttribute("msg", "Empty file selected or there is no file which is selected");
                return "result";
            }
            User user = this.userService.getUser(authentication.getName());
            Long userId = user.getUserId();

        if(fileService.filenameExists(fileUpload.getOriginalFilename(), userId)) {
            model.addAttribute("error",true);
            model.addAttribute("message","File with that name already exists");
           return "result";
        }
        try {
            fileService.storeInDB(fileUpload, userId);
            model.addAttribute("success",true);
            model.addAttribute("message","New File added successfully!");
        } catch (Exception e) {
            model.addAttribute("error",true);
            model.addAttribute("message",e.getMessage());
        }
            return "result";
    }











//    @PostMapping("/file-upload")
//    public String handleFileUpload(@RequestParam("fileUpload") MultipartFile multipartFile, File file, Model model, Authentication authentication) {
//        boolean fileUploadSuccess = true;
//        boolean fileUploadError = false ;
//        if(multipartFile.isEmpty()){
//            fileUploadError = true;
//            model.addAttribute("fileUploadError", "File is empty");
//        }else{
//            try {
//                int rowAdded = fileService.storeInDB(multipartFile,authentication);
//                System.out.println(rowAdded);
//            } catch (IOException e) {
//                fileUploadError = true;
//                e.printStackTrace();
//                model.addAttribute("fileUploadError", "There was an error in uploading file. Please Try again");
//
//            }
//            List<File> files=fileService.filesUpload(file.getUserId());
//            model.addAttribute("fileUploadSuccess","File uploaded successfully");
//            model.addAttribute("files",files);
//            System.out.println("Upload Successful");
//        }
//        return "redirect:/home";
//    }
}









