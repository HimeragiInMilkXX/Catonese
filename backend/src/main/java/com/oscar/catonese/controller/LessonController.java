package com.oscar.catonese.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.oscar.catonese.dto.Communication;
import com.oscar.catonese.dto.Conversation;
import com.oscar.catonese.dto.Grammar;
import com.oscar.catonese.dto.LessonDetails;
import com.oscar.catonese.dto.Roleplay;
import com.oscar.catonese.service.LessonService;
import com.oscar.catonese.util.ResponseUtil;

import tools.jackson.core.type.TypeReference;

import com.oscar.catonese.util.RecordUtil.AudioResult;

@RestController
@CrossOrigin( origins = "http://localhost:5173" )
@RequestMapping( "/api/lesson" )
public class LessonController {
    
    @Autowired
    private LessonService lessonService;

    @GetMapping( "/getDetails" )
    public ResponseEntity<?> getLessonsDetails() {

        try {

            Map<String, LessonDetails> map = lessonService.getLessonsDetails();
            return ResponseUtil.succeed( map );

        } catch( IOException e ) {

            return ResponseUtil.error( 500, e.getMessage() );

        }

    }

    @GetMapping( "/{lesson}/getConversationAudio")
    public ResponseEntity<?> getConversationAudio( @PathVariable( "lesson" ) String lesson ) {

        try {

            AudioResult audioResult = lessonService.getConversationAudio(lesson);

            return ResponseUtil.succeedWithFiles( audioResult.contentType(), lesson, audioResult.resource() );
        
        } catch( FileNotFoundException e ) {

            return ResponseUtil.error(404, e.getMessage() );

        } catch( ResponseStatusException e ) {

            return ResponseUtil.error( Integer.parseInt( e.getStatusCode().toString() ), e.getMessage() );

        } catch( Exception e ) {

            return ResponseUtil.badRequest( e.getMessage() );

        }

    }

    @GetMapping( "/{lesson}/getPieceAudio" )
    public ResponseEntity<?> getPieceAudio( @PathVariable( "lesson" ) String lesson, @RequestParam String name ) {

        try {

            AudioResult audioResult = lessonService.getPieceAudio( lesson, name );

            return ResponseUtil.succeedWithFiles( audioResult.contentType(), lesson, audioResult.resource() );
        
        } catch( FileNotFoundException e ) {

            return ResponseUtil.error(404, e.getMessage() );

        } catch( ResponseStatusException e ) {

            return ResponseUtil.error( Integer.parseInt( e.getStatusCode().toString() ), e.getMessage() );

        } catch( Exception e ) {

            return ResponseUtil.badRequest( e.getMessage() );

        }

    }

    @GetMapping( "/{lesson}/getRoleplay" )
    public ResponseEntity<?> getRoleplay( @PathVariable( "lesson" ) String lesson ) {

        try {

            Map<String, Roleplay> roleplay = lessonService.getJsonOfLesson(lesson, "roleplay", new TypeReference<Map<String, Roleplay>>() {} );
            return ResponseUtil.succeed( roleplay );

        } catch( IOException e ) {

            return ResponseUtil.error(500, e.getMessage() );

        }

    }

    @GetMapping( "/{lesson}/getFlashcard" )
    public ResponseEntity<?> getFlashcard( @PathVariable( "lesson" ) String lesson ) {

        try {

            Map<String, Object[][]> flashcard = lessonService.getJsonOfLesson(lesson, "flashcard", new TypeReference<Map<String, Object[][]>>() {} );
            return ResponseUtil.succeed( flashcard );

        } catch( IOException e ) {

            return ResponseUtil.error(500, e.getMessage() );

        }

    }

    @GetMapping( "/{lesson}/getGrammar")
    public ResponseEntity<?> getGrammar( @PathVariable( "lesson" ) String lesson ) {

        try {

            Map<String, Grammar> grammar = lessonService.getJsonOfLesson(lesson, "grammar", new TypeReference<Map<String, Grammar>>() {} );
            return ResponseUtil.succeed( grammar );

        } catch( IOException e ) {

            return ResponseUtil.error(500, e.getMessage() );

        }

    }

    @GetMapping( "/{lesson}/getCommunication")
    public ResponseEntity<?> getCommuncation( @PathVariable( "lesson" ) String lesson ) {

        try {

            Map<String, Communication> communication = lessonService.getJsonOfLesson(lesson, "communication", new TypeReference<Map<String, Communication>>() {} );
            return ResponseUtil.succeed( communication );

        } catch( IOException e ) {

            return ResponseUtil.error(500, e.getMessage() );

        }

    }

    @GetMapping( "/{lesson}/getConversation")
    public ResponseEntity<?> getConversation( @PathVariable( "lesson" ) String lesson ) {

        try {

            Conversation conversation = lessonService.getJsonOfLesson(lesson, "conversation", new TypeReference<Conversation>() {} );
            return ResponseEntity.ok( conversation );

        } catch( IOException e ) {

            return ResponseUtil.error(500, e.getMessage() );

        }

    }

}
