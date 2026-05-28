package com.oscar.catonese.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oscar.catonese.model.User;
import com.oscar.catonese.model.UserLessonProgress;
import com.oscar.catonese.repository.UserLessonProgressRepository;
import com.oscar.catonese.repository.UserRepository;

import cn.dev33.satoken.exception.NotLoginException;

@Service
public class ProgressService {
    
    @Autowired
    private UserLessonProgressRepository userLessonProgressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    public boolean hasLessonCompleted( User user ) {

        Long currentCompleted = userLessonProgressRepository.countByUserAndLessonAndCompletedTrue(user, user.getLesson());
        if( currentCompleted >= 4 ) return true;

        return false;

    }

    public boolean tryNextLesson( User user ) {

        if( hasLessonCompleted(user) ) {

            user.setLesson( user.getLesson() + 1 );
            userRepository.save(user);
            return true;

        }

        return false;

    }

    public Map<String, Boolean> getProgress( Integer lesson ) throws NullPointerException, NotLoginException {

        User user = authService.getLoggedInUser();

        List<UserLessonProgress> list = userLessonProgressRepository.findAllByUserAndLesson(user, lesson);

        Map<String, Boolean> map = new HashMap<>();

        for( UserLessonProgress part : list )
            map.put( part.getPart(), part.isCompleted() );

        return map;

    }

    public boolean setPartComplete( String part ) throws NullPointerException, NotLoginException {

        User user = authService.getLoggedInUser();

        if( userLessonProgressRepository.findByUserAndLessonAndPart( user, user.getLesson(), part ).orElse(null) == null )
            userLessonProgressRepository.save( new UserLessonProgress( user, user.getLesson(), part, true ) );

        if( tryNextLesson(user) ) return true;

        return false;

    }

}
