package com.diary.domain.repository;

import com.diary.domain.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface WeatherRepository extends JpaRepository<Weather, LocalDate> {

    boolean findTop1ByDateAndAreaDesc(LocalDate date, String area);
}
