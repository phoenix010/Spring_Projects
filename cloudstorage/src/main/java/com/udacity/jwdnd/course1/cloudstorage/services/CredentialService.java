package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialService {
    private UserService userService;
    private EncryptionService encryptionService;
    private final HashService hashService;
    private CredentialsMapper credentialsMapper;

    public CredentialService(UserService userService, CredentialsMapper credentialsMapper, HashService hashService, EncryptionService encryptionService) {
        this.userService = userService;
        this.credentialsMapper = credentialsMapper;
        this.hashService = hashService;
        this.encryptionService = encryptionService;
    }

    private String[] encodePassword(String password, Credentials credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setPassword(encryptedPassword);
        credential.setKey(encodedKey);
        return new String[]{encryptedPassword, encodedKey};
    }

    public long getCredIdByUserId(long userId) {
        return credentialsMapper.getCredId(userId);
    }

    public String getusernameByUserId(long userId) {
        return credentialsMapper.getUsernamebyuserId(userId);
    }

    public int deleteCreds(long credentialId, long userId) {
        return credentialsMapper.delete(credentialId, userId);
    }

    public int insertCreds(Credentials creds, long userId) {
        creds.setUserId(userId);
        creds.setUsername(creds.getUsername());
        String[] result = encodePassword(creds.getPassword(), creds);
        creds.setPassword(result[0]);
        creds.setKey(result[1]);
        return credentialsMapper.insert(creds);
    }

    public int updateCreds(Credentials creds, long userId) {
        creds.setUserId(userId);
        creds.setUsername(creds.getUsername());
        String[] result = encodePassword(creds.getPassword(), creds);
        creds.setPassword(result[0]);
        creds.setKey(result[1]);
        return credentialsMapper.update(creds);
    }

    public List<Credentials> credsUpload(Long userId) {
        List<Credentials> credentials = credentialsMapper.getCredsByUserId(userId);
        return credentials.stream().map(credential -> {
            String encodedKey = credential.getKey();
            String encryptedPassword = credential.getPassword();
            String decryptedPassword = encryptionService.decryptValue(encryptedPassword, encodedKey);
            credential.setDecryptedPassword(decryptedPassword);
            return credential;
        }).collect(Collectors.toList());
    }
}
