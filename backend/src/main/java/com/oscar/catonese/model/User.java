package com.oscar.catonese.model;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table( name = "users" )
@Data
public class User {
    
    @Id // This is primary key
    @GeneratedValue( strategy = GenerationType.IDENTITY ) // This is auto-increment
    private Long id;

    @Column( nullable = false, unique = true )
    private String email;

    @Column( nullable = false, unique = true )
    private String username;

    @Column( nullable = false )
    @JsonIgnore
    private String password;

    @Column( nullable = false )
    private Integer lesson = 0;

    @Column( nullable = false )
    private String audioTimestamp = "00:00";

    @CreationTimestamp
    @Column( updatable = false )
    private LocalDateTime createdAt;

    @Column( nullable = true )
    private String nationality;

    @Column( nullable = true )
    private String bio;

    public User() {}

    public User( String email, String username, String password ) {

        this.email = email;
        this.username = username;
        this.password = password;

    }
    
}
