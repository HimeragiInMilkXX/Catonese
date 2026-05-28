package com.oscar.catonese.dto;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Roleplay {
    
    public String question;
    public String answer;

    @Nullable
    public Object[][] ignore;
    // IGNORE STARTING FROM INDEX 2, UNTIL WORD OF INDEX 4(IGNORE PUNCTUATION) APPEARS

}
