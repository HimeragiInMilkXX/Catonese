package com.oscar.catonese.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.oscar.catonese.util.RecordUtil.AlignmentResult;

@Service
public class AlignmentService {
    
    private final WebClient webClient;

    public AlignmentService( WebClient.Builder webClientBuilder, @Value("${alignment.base-url}") String baseUrl ) {

        this.webClient = webClientBuilder.baseUrl( baseUrl ).build();

    }

    public AlignmentResult upload( MultipartFile audioFile, String text ) throws Exception {

        MultipartBodyBuilder builder = new MultipartBodyBuilder();

        builder.part( "audio", audioFile.getResource() )
                .filename( audioFile.getOriginalFilename() )
                .contentType( MediaType.parseMediaType("audio/webm"));

        builder.part( "text", text );

        AlignmentResult result = webClient.post()
                        .uri("/align")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .body( BodyInserters.fromMultipartData( builder.build() ) )
                        .retrieve()
                        .bodyToMono( AlignmentResult.class )
                        .block();

        return result;

    }

}
