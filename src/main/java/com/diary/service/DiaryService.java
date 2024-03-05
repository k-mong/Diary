package com.diary.service;

import com.diary.domain.entity.Diary;
import com.diary.domain.entity.User;
import com.diary.domain.entity.Weather;
import com.diary.domain.repository.DiaryRepository;
import com.diary.domain.repository.UserRepository;
import com.diary.domain.repository.WeatherRepository;
import com.diary.dto.DiaryInfoDto;
import com.diary.dto.DiaryResponseDto;
import com.diary.dto.DiaryUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final OpenApiService openApiService;
    private final WeatherRepository weatherRepository;
    private final UserRepository userRepository;
    private final DiaryRepository diaryRepository;


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

    public Diary createDiary(DiaryInfoDto diaryInfoDto, String userId) {
        // 1. 날씨정보 갖고오기
        Weather weather = new Weather();

        // 1-1 이미 있는지 확인
        if(weatherRepository.existsByDateAndArea(LocalDate.now(), diaryInfoDto.getArea())) {

            Optional<Weather> findWeather = weatherRepository.findTopByDateAndAreaOrderByDateDesc(LocalDate.now(), diaryInfoDto.getArea());
            weather = findWeather.get();
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

    public List<DiaryResponseDto> diaryList(String userId) {
        Optional<User> user = userRepository.findByEmail(userId);
        List<Diary>diarys = diaryRepository.findByUser(user.get());
        List<DiaryResponseDto> diaryResponseDtos = new ArrayList<>();
        for (Diary diary : diarys) {
            DiaryResponseDto diaryResponseDto = DiaryResponseDto.builder()
                    .title(diary.getTitle())
                    .content(diary.getContent())
                    .icon(diary.getIcon())
                    .temp(diary.getTemp())
                    .weather(diary.getWeather())
                    .date(diary.getDate())
                    .user(user.get())
                    .build();

            diaryResponseDtos.add(diaryResponseDto);
        }
        return diaryResponseDtos;

    }

    public String updateDiary(DiaryUpdateDto diaryUpdateDto, String userId, Long diaryId){
        User user = userRepository.findByEmail(userId).get();
        Diary findDiary = diaryRepository.findById(diaryId).get();

        if(!findDiary.getUser().equals(user)) {
            throw new RuntimeException("유저가 없습니다.");
        }

        // 방법 1. Setter
//        findDiary.setTitle(diaryUpdateDto.getTitle());
//        findDiary.setContent(diaryUpdateDto.getContent());
//

//
        // 방법 2. 생성자
        findDiary.update(diaryUpdateDto);

        // 방법 3 builder
//        diaryRepository.save(findDiary.builder()
//                        .id(findDiary.getId())
//                        .user(findDiary.getUser())
//                        .title(diaryUpdateDto.getTitle())
//                .content(diaryUpdateDto.getContent())
//                        .weather(findDiary.getWeather())
//                        .icon(findDiary.getIcon())
//                        .temp(findDiary.getTemp())
//                        .date(findDiary.getDate())
//                .build());

        diaryRepository.save(findDiary);

        return "다이어리 수정 완료";
    }



    public String deleteDiary(Long diaryId,String userId) {
        User user = userRepository.findByEmail(userId).get();
        Diary diary = diaryRepository.findByIdAndUser(diaryId, user).get();

        if(!diary.getUser().equals(user)) {
            throw new RuntimeException("유저가 없어요");
        }

            diaryRepository.deleteById(diaryId);
            return "다이어리 삭제 완료";


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
