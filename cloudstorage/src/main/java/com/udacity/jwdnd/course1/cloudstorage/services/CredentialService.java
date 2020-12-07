package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private  UserService userService;
    private final HashService hashService;
    private CredentialsMapper credentialsMapper;
    public CredentialService(UserService userService, CredentialsMapper credentialsMapper,HashService hashService) {
        this.userService = userService;
        this.credentialsMapper = credentialsMapper;
        this.hashService = hashService;
    }

    private String[] encodePassword(String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(password, encodedSalt);
        return new String[]{hashedPassword, encodedSalt};
    }
    public int insert(Credentials creds , long userId){
        creds.setUserId(userId);
        String[] result = encodePassword(creds.getPassword());
        creds.setPassword(result[0]);
        creds.setKey(result[1]);
        return credentialsMapper.insert(creds);
    }
    public List<Credentials> credsUpload(Long userId){
        return CredentialsMapper .getCredsByUserId(userId);  }
}
