package com.oscar.catonese.repository;

import com.oscar.catonese.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername( String username );
    boolean existsByUsername( String username );

    Optional<User> findByEmail( String email );
    boolean existsByEmail( String email );

}
