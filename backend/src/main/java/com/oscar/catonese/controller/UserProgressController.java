package com.oscar.catonese.controller;

import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oscar.catonese.service.ProgressService;
import com.oscar.catonese.util.ResponseUtil;

import cn.dev33.satoken.exception.NotLoginException;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin( origins = "http://localhost:5173" )
public class UserProgressController {
    
    private final ProgressService progressService;

    public UserProgressController( ProgressService progressService ) { this.progressService = progressService; }

    @PutMapping( "/complete/{part}" )
    public ResponseEntity<?> setPartComplete( @PathVariable( "part" ) String part ) {
        
        try {

            boolean satisfyNextLesson = progressService.setPartComplete(part);

            return ResponseUtil.succeed( Map.of(

                "part", part + "part completed!",
                "isNextLesson", satisfyNextLesson

            ) );

        } catch( NullPointerException e ) { return ResponseUtil.error( 404, e.getMessage() );
        } catch( NotLoginException e ) { return ResponseUtil.badRequest( e.getMessage() ); }

    }

    @GetMapping( "/get/{lesson}" )
    public ResponseEntity<?> getProgress( @PathVariable("lesson") String lesson ) {
        
        try {

            return ResponseUtil.succeed(progressService.getProgress( Integer.parseInt(lesson) ) );

        } catch( NullPointerException e ) { return ResponseUtil.error( 404, e.getMessage() );
        } catch( NotLoginException e ) { return ResponseUtil.badRequest( e.getMessage() ); }

    }


}
