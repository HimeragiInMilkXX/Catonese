package com.oscar.catonese.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.oscar.catonese.util.RecordUtil.AudioResult;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
public class StorageService {
    
    private final Path root;

    private final ObjectMapper objectMapper;

    public StorageService( @Value("${lessons.content-folder}") String contentFolder, ObjectMapper objectMapper ) throws IOException {

        this.root = Paths.get( contentFolder );
        if( !Files.exists( root ) ) Files.createDirectories(root);

        this.objectMapper = objectMapper;

    }

    public <T> T getJsonMap( String filename, TypeReference<T> type ) throws IOException {

        Path dir = root.resolve( filename + ".json" ).normalize();

        InputStream inputStream = Files.newInputStream( dir );

        T json = objectMapper.readValue( inputStream, type );

        inputStream.close();

        return json;

    }


    public AudioResult getConversationAudio( String lesson ) throws Exception {

        Path filename = root.resolve( lesson ).resolve( "conversation.wav" ).normalize();
        Resource resource = new UrlResource( filename.toUri() );

        return getAudio( filename, resource);

    }

    public AudioResult getPieceAudio( String lesson, String name ) throws Exception {

        Path filename = root.resolve( lesson ).resolve( "conversationPieces" ).resolve( name + ".m4a" ).normalize();
        Resource resource = new UrlResource( filename.toUri() );

        return getAudio( filename, resource );

    }

    private AudioResult getAudio( Path filename, Resource resource ) throws Exception {

        if( resource.exists() && resource.isReadable() ) {

            String contentType = Files.probeContentType( filename );
            if( contentType == null )
                throw new ResponseStatusException( HttpStatus.UNSUPPORTED_MEDIA_TYPE );

            return new AudioResult( MediaType.parseMediaType(contentType), resource);
        }

        throw new FileNotFoundException( "Audio file not found" );

    }

}
