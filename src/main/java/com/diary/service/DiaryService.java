package com.diary.service;

import com.diary.domain.entity.Diary;
import com.diary.domain.entity.User;
import com.diary.domain.repository.UserRepository;
import com.diary.domain.repository.WeatherRepository;
import com.diary.dto.DiaryInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

        } else {
            User user = userRepository.findByEmail(diaryInfoDto.getUser_id()).get();

            Diary diary = new Diary();


        }

        // 다이어리 저장
        return null;
    }
}
