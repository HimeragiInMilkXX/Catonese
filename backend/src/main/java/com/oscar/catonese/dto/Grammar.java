package com.oscar.catonese.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Grammar {

    public String grammar;
    public String explanation;
    public String[][] examples;
    public String[][] exercises;

}