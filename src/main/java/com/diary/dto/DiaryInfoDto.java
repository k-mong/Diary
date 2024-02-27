package com.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiaryInfoDto {

    private String title;
    private String content;
    private String area;
}
