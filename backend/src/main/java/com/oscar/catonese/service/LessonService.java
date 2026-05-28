package com.oscar.catonese.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oscar.catonese.dto.LessonDetails;
import com.oscar.catonese.util.RecordUtil.AudioResult;

import tools.jackson.core.type.TypeReference;

@Service
public class LessonService {
    
    @Autowired
    private StorageService storageService;

    public Map<String, LessonDetails> getLessonsDetails() throws IOException {

        return storageService.getJsonMap( "lessons", new TypeReference<Map<String, LessonDetails>>() {} );

    }

    public AudioResult getConversationAudio( String lesson ) throws Exception {

        return storageService.getConversationAudio( lesson );

    }

    public AudioResult getPieceAudio( String lesson, String name ) throws Exception {

        return storageService.getPieceAudio(lesson, name);

    }

    public <T> T getJsonOfLesson( String lesson, String filename, TypeReference<T> type ) throws IOException {

        String path = lesson + "/" + filename;

        return storageService.getJsonMap( path, type );

    }

}
