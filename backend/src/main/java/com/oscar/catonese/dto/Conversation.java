package com.oscar.catonese.dto;

import com.oscar.catonese.util.RecordUtil.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Conversation {
    
    public Role a;
    public Role b;

}
