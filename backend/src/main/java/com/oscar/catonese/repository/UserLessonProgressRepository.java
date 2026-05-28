package com.oscar.catonese.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscar.catonese.model.User;
import com.oscar.catonese.model.UserLessonProgress;

public interface UserLessonProgressRepository extends JpaRepository<UserLessonProgress, Long> {
    
    Optional<UserLessonProgress> findByUserAndLessonAndPart( User user, Integer lesson, String part );
    List<UserLessonProgress> findAllByUserAndLesson( User user, Integer lesson );
    long countByUserAndLessonAndCompletedTrue( User user, Integer lesson );

}
