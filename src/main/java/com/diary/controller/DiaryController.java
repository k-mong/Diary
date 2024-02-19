package com.diary.controller;

import com.diary.service.OpenApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {

    private final OpenApiService openApiService;

    @GetMapping("/test")
    public ResponseEntity<String > test() {
        return ResponseEntity.ok(openApiService.getWeatherString("seoul"));
    }
}
