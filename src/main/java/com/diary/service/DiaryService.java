package com.diary.service;

import com.diary.domain.entity.Diary;
import com.diary.domain.entity.User;
import com.diary.domain.entity.Weather;
import com.diary.domain.repository.DiaryRepository;
import com.diary.domain.repository.UserRepository;
import com.diary.domain.repository.WeatherRepository;
import com.diary.dto.DiaryInfoDto;
import com.diary.dto.DiaryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final OpenApiService openApiService;
    private final WeatherRepository weatherRepository;
    private final UserRepository userRepository;
    private final DiaryRepository diaryRepository;


    public Diary createDiary(DiaryInfoDto diaryInfoDto, String userId) {
        // 1. 날씨정보 갖고오기
        Weather weather = new Weather();

        // 1-1 이미 있는지 확인
        if(weatherRepository.existsByDateAndArea(LocalDate.now(), diaryInfoDto.getArea())) {

            weatherRepository.findTopByDateAndAreaOrderByDateDesc(LocalDate.now(), diaryInfoDto.getArea());

        } else {
            // 1-2 없을경우, 저장 후 가져오기
            weather = getWeatherInfo(diaryInfoDto.getArea());
        }

            User user = userRepository.findByEmail(userId).get();

            Diary diary = new Diary();
            diary.setTitle(diaryInfoDto.getTitle());
            diary.setContent(diaryInfoDto.getContent());
            diary.setDate(LocalDate.now());
            diary.setDateInfo(weather);
            diary.setUser(user);

        // 다이어리 저장
        return diaryRepository.save(diary);
    }

    public Diary updateDiary(){
        return null;
    }

//    public Diary createDiary(DiaryInfoDto diaryInfoDto, String userId) {
//        // 1. 날씨정보 갖고오기
//        Weather weather = new Weather();
//
//        // 1-1 이미 있는지 확인
//
//        if(weatherRepository.existsByDateAndArea(LocalDate.now(), diaryInfoDto.getArea())) {
//            // 있는 날씨정보 갖고오기
//            weather = weatherRepository.findTop1ByDateAndAreaOrderByDateDesc(LocalDate.now(), diaryInfoDto.getArea()).get();
//        } else {
//            // 1-2 없을경우, 저장 후 가져오기
//            weather = getWeatherInfo(diaryInfoDto.getArea());
//        }
//        User user = userRepository.findByEmail(userId).get();
//        // 다이어리 저장
//        return diaryRepository.save(
//                Diary.builder()
//                        .title(diaryInfoDto.getTitle())
//                        .content(diaryInfoDto.getContent())
//                        .date(LocalDate.now())
//                        .icon(weather.getIcon())
//                        .temp(weather.getTemp())
//                        .weather(weather.getWeather())
//                        .user(user)
//                        .build());
//
//
//
//    }

    public List<DiaryResponseDto> diaryList() {
        List<Diary>diarys = diaryRepository.findAll();
        List<DiaryResponseDto> diaryResponseDtos = new ArrayList<>();
        for (Diary diary : diarys) {
            DiaryResponseDto diaryResponseDto = DiaryResponseDto.builder()
                    .title(diary.getTitle())
                    .content(diary.getContent())
                    .icon(diary.getIcon())
                    .temp(diary.getTemp())
                    .weather(diary.getWeather())
                    .date(diary.getDate())
                    .user(diary.getUser())
                    .build();

            diaryResponseDtos.add(diaryResponseDto);
        }
        return diaryResponseDtos;

    }



    public Weather getWeatherInfo (String area) {

        String getWeather = openApiService.getWeatherString(area);

        HashMap<String, Object> weatherInfo = openApiService.jsonParseString(getWeather);
        Weather weather = new Weather();
        weather.setDate(LocalDate.now());
        weather.setIcon(weatherInfo.get("icon").toString());
        weather.setTemp((Double) weatherInfo.get("temp"));
        weather.setWeather(weatherInfo.get("main").toString());
        weather.setArea(area);

        return weatherRepository.save(weather);


    }
}
