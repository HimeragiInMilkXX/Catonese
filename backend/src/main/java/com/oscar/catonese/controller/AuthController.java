package com.oscar.catonese.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oscar.catonese.dto.LoginRequest;
import com.oscar.catonese.dto.RegisterRequest;
import com.oscar.catonese.model.User;
import com.oscar.catonese.repository.UserRepository;
import com.oscar.catonese.util.PasswordUtil;
import com.oscar.catonese.util.ResponseUtil;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin( origins = "http://localhost:5173" )
public class AuthController {
    
    private final UserRepository userRepository;
    
    public AuthController( UserRepository userRepository ) { this.userRepository = userRepository; }

    @PostMapping("/register")
    public ResponseEntity<?> register( @Valid @RequestBody RegisterRequest body ) {

        String email = body.getEmail();
        String username = body.getUsername();
        String password = body.getPassword();

        if( userRepository.existsByUsername(username) ) return ResponseUtil.badRequest( "Username already taken!" );
        if( userRepository.existsByEmail(email) ) return ResponseUtil.badRequest( "Email already taken!" );

        // SAVE INTO DB
        User user = new User( email, username, PasswordUtil.hash(password) );
        userRepository.save( user );

        // CREATE SESSION
        StpUtil.login( user.getId() );
        String token = StpUtil.getTokenValue();

        return ResponseUtil.succeed( Map.of(

            "token", token,
            "userId", user.getId(),
            "username", user.getUsername(),
            "email", user.getEmail(),
            "lesson", user.getLesson()

        ) );

    }

    @PostMapping("/login")
    public ResponseEntity<?> login( @Valid @RequestBody LoginRequest body ) {

        String identifier = body.getIdentifier();
        String password = body.getPassword();

        User user;

        if( identifier.contains( "@" ) )
            user = userRepository.findByEmail(identifier).orElse(null);
        else
            user = userRepository.findByUsername(identifier).orElse(null);

        if( user == null ) return ResponseUtil.badRequest( "User not found!" );
        if( !PasswordUtil.matches(password, user.getPassword()) ) return ResponseUtil.badRequest("Inavlid password");

        StpUtil.login( user.getId() );
        String token = StpUtil.getTokenValue();

        return ResponseUtil.succeed( Map.of(

            "token", token,
            "userId", user.getId(),
            "username", user.getUsername(),
            "email", user.getEmail(),
            "lesson", user.getLesson()

        ) );

    }

    @PostMapping("/logout")
    @SaCheckLogin
    public ResponseEntity<?> logout() {

        if( !StpUtil.isLogin() ) return ResponseUtil.error( 401, "Not logged in" );

        StpUtil.logout();

        return ResponseUtil.succeed( Map.of( "message", "Logged out" ) );

    }

    @GetMapping("/me")
    @SaCheckLogin
    public ResponseEntity<?> me() {

        if( !StpUtil.isLogin() ) return ResponseUtil.error( 401, "Not logged in" );

        Object loginId = StpUtil.getLoginId();
        Long userId = Long.valueOf( loginId.toString() );

        User user = userRepository.findById( userId ).orElse(null);
        if( user == null ) return ResponseUtil.error( 404, "User not found" );

        return ResponseUtil.succeed( Map.of(

            "userId", user.getId(),
            "email", user.getEmail(),
            "username", user.getUsername(),
            "lesson", user.getLesson()

        ) );

    }

}
