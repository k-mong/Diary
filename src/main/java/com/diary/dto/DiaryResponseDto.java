package com.diary.dto;

import com.diary.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class DiaryResponseDto {
    private String title;
    private String content;
    @JsonIgnore
    private User user;
    private LocalDate date;
    private String icon;
    private String weather;
    private double temp;
}
