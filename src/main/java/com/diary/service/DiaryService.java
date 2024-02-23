package com.diary.service;

import com.diary.domain.entity.Diary;
import com.diary.domain.entity.User;
import com.diary.domain.entity.Weather;
import com.diary.domain.repository.UserRepository;
import com.diary.domain.repository.WeatherRepository;
import com.diary.dto.DiaryInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final OpenApiService openApiService;
    private final WeatherRepository weatherRepository;
    private final UserRepository userRepository;


    public Diary createDiary(DiaryInfoDto diaryInfoDto) {
        // 1. 날씨정보 갖고오기
        // 1-1 이미 있는지 확인
        // 1-2 없을경우, 저장 후 가져오기
        if(weatherRepository.findTop1ByDateAndAreaDesc(LocalDate.now(), diaryInfoDto.getArea())) {
            throw new RuntimeException("에러");
        } else {
            User user = userRepository.findByEmail(diaryInfoDto.getUser_id()).get();

            Diary diary = new Diary();
            diary.setTitle(diary.getTitle());
            diary.setContent(diary.getContent());
            diary.setUser(user);
            diary.setWeather(getWeatherInfo(diaryInfoDto));

            // 다이어리 저장
            return diary;
        }


    }

    public HashMap<String, Object> getWeatherInfo() {
        Weather weather = new Weather();
        weather.setWeather(weatherInfo.get("main").toString());
        weather.setDate(LocalDate.now());
        weather.setIcon(weatherInfo.get("icon").toString());
        weather.setTemp(Double.parseDouble(weatherInfo.get("temp").toString()));

        return ;
    }
}
