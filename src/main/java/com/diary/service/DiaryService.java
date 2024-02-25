package com.diary.service;

import com.diary.domain.entity.Diary;
import com.diary.domain.entity.User;
import com.diary.domain.entity.Weather;
import com.diary.domain.repository.DiaryRepository;
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
    private final DiaryRepository diaryRepository;


//    public Diary createDiary(DiaryInfoDto diaryInfoDto) {
//        // 1. 날씨정보 갖고오기
//        Weather weather = getWeatherInfo(diaryInfoDto);
//
//        // 1-1 이미 있는지 확인
//        if(weatherRepository.findTop1ByDateAndWeatherOrderByDateDesc(LocalDate.now(), diaryInfoDto.getArea())) {
//            throw new RuntimeException("날씨정보가 이미 있습니다.");
//        } else {
//            // 1-2 없을경우, 저장 후 가져오기
//            User user = userRepository.findByEmail(diaryInfoDto.getUser_id()).get();
//
//            Diary diary = new Diary();
//            diary.setTitle(diary.getTitle());
//            diary.setContent(diary.getContent());
//            diary.setDate(LocalDate.now());
//            diary.setWeather(weather.getWeather());
//            diary.setUser(user);
//
//        }
//        // 다이어리 저장
//        return null;
//    }

    public Diary createDiary(DiaryInfoDto diaryInfoDto) {
        // 1. 날씨정보 갖고오기
        Weather weather = getWeatherInfo(diaryInfoDto);

        // 1-1 이미 있는지 확인

        if(weatherRepository.findTop1ByDateAndWeatherOrderByDateDesc(LocalDate.now(), weather.getWeather())) {
            throw new RuntimeException("날씨정보가 이미 있습니다.");
        } else {
            // 1-2 없을경우, 저장 후 가져오기
            User user = userRepository.findByEmail(diaryInfoDto.getUser_id()).get();

            // 다이어리 저장
            return diaryRepository.save(
                    Diary.builder()
                            .title(diaryInfoDto.getTitle())
                            .content(diaryInfoDto.getContent())
                            .date(LocalDate.now())
                            .weather(weather.getWeather())
                            .user(user)
                            .build());

        }

    }

    public Weather getWeatherInfo (DiaryInfoDto diaryInfoDto) {
        System.out.println("getWeatherInfo 실행!!");

        String getWeather = openApiService.getWeatherString(diaryInfoDto.getArea());

        HashMap<String, Object> weatherInfo = openApiService.jsonParseString(getWeather);
        Weather weather = new Weather();
        weather.setDate(LocalDate.now());
        weather.setIcon(weatherInfo.get("icon").toString());
        weather.setTemp((Double) weatherInfo.get("temp"));
        weather.setWeather(weatherInfo.get("main").toString());

        System.out.println(diaryInfoDto.getArea());
        System.out.println(weather);
        return weather;

    }
}
