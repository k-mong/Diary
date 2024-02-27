package com.diary.domain.repository;

import com.diary.domain.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, LocalDate> {

    boolean existsByDateAndArea(LocalDate date, String area);
    Optional<Weather> findTop1ByDateAndAreaOrderByDateDesc(LocalDate date, String area);
}
