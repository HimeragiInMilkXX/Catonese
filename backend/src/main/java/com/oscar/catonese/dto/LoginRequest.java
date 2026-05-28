package com.oscar.catonese.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
    
    @NotBlank( message = "Identifier (email or username) is required!" )
    private String identifier;

    @NotBlank( message = "Password is required" )
    private String password;

}
