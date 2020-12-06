package com.udacity.jwdnd.course1.cloudstorage.model;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;

import java.util.List;

public class File {
    private Long fileId;
    private Long userId;
    private String fileName;
    private String contentType;
    private long fileSize;
    private byte[] fileData;
//    private String date;


public File(){}
//    public File(String fileName, String contentType, long fileSize, Long userId, byte[] fileData) {
//        this.userId = userId;
//        this.fileName = fileName;
//        this.contentType = contentType;
//        this.fileSize = fileSize;
//        this.fileData = fileData;
//    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public void setUserid(long userid) { this.userId = userid; }

    public Long getUserId() {return userId; }

}
