package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class FileDownloadController {
    private  FileService fileService;
    private UserService userService;

    public FileDownloadController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }





}
