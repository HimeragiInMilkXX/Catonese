package com.oscar.catonese.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.oscar.catonese.service.AlignmentService;
import com.oscar.catonese.util.RecordUtil.AlignmentResult;
import com.oscar.catonese.util.ResponseUtil;

@RestController
@CrossOrigin( origins = "http://localhost:5173" )
@RequestMapping( "/api/alignment" )
public class AlignmentController {
    
    private final AlignmentService alignmentService;

    public AlignmentController( AlignmentService alignmentService ) { this.alignmentService = alignmentService; }

    @PostMapping( value = "/align", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<?> align( @RequestPart( value = "audio" ) MultipartFile audio, @RequestParam( value = "text" ) String text ) {

        try {

            AlignmentResult result = alignmentService.upload(audio, text);
            return ResponseUtil.succeed( Map.of( "alignmentResult", result ) );

        } catch( Exception e ) {

            return ResponseUtil.error( 500, e.getMessage() );

        }

    }

}
