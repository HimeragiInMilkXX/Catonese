package com.oscar.catonese.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LessonDetails {

    public String name;

    public List<String> goals;

}