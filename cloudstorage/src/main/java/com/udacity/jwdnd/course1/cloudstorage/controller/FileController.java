package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


import javax.naming.SizeLimitExceededException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller

public class FileController  implements HandlerExceptionResolver {

    private final FileService fileService;
    private UserService userService;


    public FileController(FileService fileService, UserService userService) {

        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/file-upload")
    public String handleFileUpload(@RequestParam("fileUpload") MultipartFile fileUpload, Model model, Authentication authentication){
            if(fileUpload.isEmpty()){
                System.out.println("Empty file");
                model.addAttribute("error", true);
                model.addAttribute("message", "Empty file selected or there is no file which is selected");
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
        }catch (Exception e) {
            model.addAttribute("error",true);
            model.addAttribute("message",e.getMessage());
        }
//        catch(MaxUploadSizeExceededException s){
//            model.addAttribute("error",true);
//            model.addAttribute("message","File Limit exceeded");
//        }
        return "result";
    }

    @GetMapping("/deleteFile/{fileId}")
    public String  handleFileDelete(@PathVariable("fileId") Long fileId, Model model) {
        try{
            this.fileService.deleteFile(fileId);
            model.addAttribute("success",true);
            model.addAttribute("message", "File deleted!");
        }catch(Exception e){
            model.addAttribute("error",true);
            model.addAttribute("message","System error!" + e.getMessage());
        }
        return "result";
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource>  handleFileDownload(@PathVariable("fileId") Integer fileId) {

        File file = fileService.findByFileId(fileId);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = " + file.getFileName());
        header.add("Cache-control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        ByteArrayResource resource = new ByteArrayResource((file.getFileData()));
        return ResponseEntity.ok()
                .headers(header)
                .body(resource);
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView("result");
        if (ex instanceof MaxUploadSizeExceededException) {
            modelAndView.getModel().put("error", true);
            modelAndView.getModel().put("message", "File size exceeds limit!");
        }
        return modelAndView;
    }
}









