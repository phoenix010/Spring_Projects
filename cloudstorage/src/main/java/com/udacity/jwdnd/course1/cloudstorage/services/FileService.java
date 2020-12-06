package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.LoginForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class FileService {
    private final UserService userService;
    private final FileMapper fileMapper;
    private LoginForm loginForm;

    public FileService(UserService userService, FileMapper fileMapper){
        this.userService = userService;
        this.fileMapper = fileMapper;
    }
    public List<File> filesUpload(Long userId){ return fileMapper.getFilesByUserId(userId);  }

    public Boolean filenameExists(String filename, Long userid) {
//        ArrayList<File> result = fileMapper.getByUsername(filename, userid);
        return !fileMapper.getByUsername(filename, userid).isEmpty();
    }

    public int storeInDB(MultipartFile fileUpload, Long userId) throws IOException {
        File file = new File();
        file.setFileName(fileUpload.getOriginalFilename());
        file.setContentType(fileUpload.getContentType());
        file.setFileSize(fileUpload.getSize());
        file.setFileData(fileUpload.getBytes());
        file.setUserid(userId);
        return fileMapper.insertFile(file);

    }

    public String getCurrentTimeUsingDate() {
        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
//        System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);
        return formattedDate;
    }


















}
