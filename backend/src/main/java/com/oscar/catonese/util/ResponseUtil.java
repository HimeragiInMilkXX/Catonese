package com.oscar.catonese.util;

import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ResponseEntity<?> badRequest( String message ) { return ResponseEntity.badRequest().body( Map.of( "success", false, "message", message ) ); }
    public static ResponseEntity<?> error( int status, String message ) { return ResponseEntity.status(status).body( Map.of( "success", false, "message", message ) ); }
    public static ResponseEntity<?> succeed( Map<String, ?> data ) { return ResponseEntity.ok( Map.of( "success", true, "data", data ) ); }
    public static ResponseEntity<?> succeedWithFiles( MediaType contentType, String filename, Resource resource ) {

        return ResponseEntity.ok()
                .contentType( contentType )
                .header( HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                .body( resource );

    }

}
