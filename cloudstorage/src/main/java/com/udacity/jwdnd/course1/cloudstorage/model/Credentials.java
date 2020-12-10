package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credentials {
    private String url;
    private Long credentialId;
    private String username;
    private String key;
    private String password;
    private long userId;
    private String decryptedPassword;

    public String getDecryptedPassword() {
        return decryptedPassword;
    }

    public void setDecryptedPassword(String decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(long credentialId) {
        this.credentialId = credentialId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
