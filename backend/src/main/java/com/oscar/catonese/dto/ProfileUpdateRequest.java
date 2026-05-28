package com.oscar.catonese.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileUpdateRequest {
    
    @NotBlank( message = "Email is required!" )
    private String email;

    @NotBlank( message = "Username is required!" )
    private String username;
    
    private String nationality;
    private String bio;

    private MultipartFile avatar;

}
