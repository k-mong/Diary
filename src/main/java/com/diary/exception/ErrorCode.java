package com.diary.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    ALREADY_REGISTER_USER(HttpStatus.BAD_REQUEST, "이미 존재하는 회원 입니다."),
    NOT_FOUND_ID(HttpStatus.NOT_FOUND, "존재하지 않는 사용자 입니다."),
    NOT_FOUND_PW(HttpStatus.NOT_FOUND, "비밀번호가 틀렸습니다"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에러 발생");

    private final HttpStatus httpStatus;
    private final String detail;
}
