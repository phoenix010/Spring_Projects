package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.SignupForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.Base64;

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

//        this.user.setFirstName(signupForm.getFirstname());
//        this.user.setLastName(signupForm.getLastname());
//        this.user.setUsername(signupForm.getUsername());
//        this.user.setPassword(signupForm.getPassword());

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(signupForm.getPassword(), encodedSalt);
        return userMapper.insert(new User(null, signupForm.getUsername(), encodedSalt, hashedPassword, signupForm.getFirstname(), signupForm.getLastname()));
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }
}
