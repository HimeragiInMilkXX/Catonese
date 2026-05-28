package com.oscar.catonese.util;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

public class RecordUtil {

    public static record AudioResult( MediaType contentType, Resource resource ) {}

    public static record AlignmentResult(

        String status,
        String transcription,
        String reference_text,
        Alignment[] alignment,
        Double audio_duration,
        ConfidenceStatus confidence_stats

    ) {

        public record Alignment(

            String token,
            Double start,
            Double end,
            Double confidence

        ) {}

        public record ConfidenceStatus(

            Double mean,
            Double min,
            Double max

        ) {}

    }

    public static record Role(

        String name,
        String intro,
        Line[] lines

    ) {}

    public static record Line(

        String id,
        String timestamp,
        String content

    ) {}

}
