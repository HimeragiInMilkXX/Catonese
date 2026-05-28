package com.oscar.catonese.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterRequest {
    
    @NotBlank( message = "Email is required!" )
    private String email;

    @NotBlank( message = "Username is required!" )
    private String username;

    @NotBlank( message = "Password is required!" )
    private String password;

}
