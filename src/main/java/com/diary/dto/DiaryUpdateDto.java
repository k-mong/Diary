package com.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiaryUpdateDto {
    private String title;
    private String content;
}
