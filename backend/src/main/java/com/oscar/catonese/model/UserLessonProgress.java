package com.oscar.catonese.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table( name = "userLessonProgress", uniqueConstraints = {

    @UniqueConstraint( columnNames = { "user_id", "lesson", "part" } )

} )
@Data
public class UserLessonProgress {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne
    @JoinColumn( name = "user_id", nullable = false )
    private User user;

    @Column( nullable = false )
    private Integer lesson;

    @Column( nullable = false )
    private String part;

    @Column( nullable = false )
    private boolean completed = false;

    @CreationTimestamp
    @Column( updatable = false )
    private LocalDateTime completedAt;

    public UserLessonProgress() {}

    public UserLessonProgress( User user, Integer lesson, String part, boolean completed ) {

        this.user = user;
        this.lesson = lesson;
        this.part = part;
        this.completed = completed;

    }

}
