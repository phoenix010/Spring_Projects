package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.LoginForm;
import com.udacity.jwdnd.course1.cloudstorage.model.SignupForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final HashService hashService;
//    private  User user;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
//        this.user = user;
    }



    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    public int createUser(SignupForm signupForm) {
        String[] result = encodePassword(signupForm.getPassword());
        return userMapper.insert(new User(null, signupForm.getUsername(), result[1], result[0], signupForm.getFirstname(), signupForm.getLastname()));
    }
    private String[] encodePassword(String password){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(password, encodedSalt);
        return new String[] {hashedPassword,encodedSalt};
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }
    public List<String> getPasswordList(String username){
        return userMapper.passwordList(username);
    }
    public List<String> getUsernameList(String username){
        return userMapper.usernameList(username);
    }

    public boolean validateUser(LoginForm loginForm){
        String [] result = encodePassword(loginForm.getPassword());
        String hashedPassword = result[0];
        List<String> usernameList= getUsernameList(loginForm.getUsername());
        List<String> passwordList = getPasswordList(loginForm.getUsername());
        for(String name:usernameList){
            if(loginForm.getUsername().equalsIgnoreCase(name)){
                for(String password: passwordList){
                    if(hashedPassword.equalsIgnoreCase(password)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
//    public int createUser(SignupForm signupForm) {
//        String[] result = encodePassword(signupForm.getPassword());
//        SecureRandom random = new SecureRandom();
//        byte[] salt = new byte[16];
//        random.nextBytes(salt);
//        String encodedSalt = Base64.getEncoder().encodeToString(salt);
//         String hashedPassword = hashService.getHashedValue(signupForm.getPassword(), encodedSalt);
//        return userMapper.insert(new User(null, signupForm.getUsername(), result[1], result[0], signupForm.getFirstname(), signupForm.getLastname()));
//    }
}
