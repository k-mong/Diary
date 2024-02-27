package com.diary.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Entity
@Setter
@NoArgsConstructor
public class Weather {

    @Id
    private LocalDate date;
    private String icon;
    private String weather;
    private double temp;
    private String area;
}
