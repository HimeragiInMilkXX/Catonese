package com.oscar.catonese.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {
    
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    // Encrypt password to store in database
    public static String hash( String rawPassword ) { return encoder.encode(rawPassword); }

    // Compare raw password with stored hash
    public static boolean matches( String rawPassword, String hashedPassword ) { return encoder.matches( rawPassword, hashedPassword ); }

}
