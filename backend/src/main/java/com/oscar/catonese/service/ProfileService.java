package com.oscar.catonese.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oscar.catonese.model.User;
import com.oscar.catonese.repository.UserRepository;

import cn.dev33.satoken.exception.NotLoginException;

@Service
public class ProfileService {
    
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    public boolean updateValues( String email, String username, String bio, String nationality ) throws NotLoginException, NullPointerException {

        User user = authService.getLoggedInUser();

        if( email != null ) user.setEmail(email);
        if( username != null ) user.setUsername(username);
        if( nationality != null ) user.setNationality(nationality);
        if( bio != null ) user.setBio(bio);

        if( userRepository.save(user) != null ) return true;
        else return false;

    }

}
