package com.diary.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException { // RuntimeException 을 상속받아 CustomException 을 사용하면 예외 발생

    private final ErrorCode errorCode;  // Enum ErrorCode 의존성 주입

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getDetail());   // 상속받은 RuntimeException 의 생성자를 호출하여 초기화한 것에 errorCode.getDetail 을 넣어준다
        this.errorCode = errorCode; // errorCode 는 매개변수로 받은 errorCode
    }
}
