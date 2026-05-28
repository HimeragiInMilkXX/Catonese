package com.oscar.catonese.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oscar.catonese.model.User;
import com.oscar.catonese.repository.UserRepository;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;

    public User getLoggedInUser() {

        if( !StpUtil.isLogin() ) throw new NotLoginException( "Not Logged In", "user", null );

        User user = userRepository.findById( StpUtil.getLoginIdAsLong() ).orElse(null);

        if( user == null ) throw new NullPointerException( "User not found" );

        return user;

    }

}
