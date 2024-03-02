package com.diary.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice   // 프로그램 전역에서 발생하는 예외처리 해주는 컨트롤러
@Slf4j
public class ExceptionController {

    // ExceptionHandler 어노테이션은 지정된 클래스에서 발생하는 에러를 잡아서 메소드로 처리해주는 기능을 제공한다.
    @ExceptionHandler({
            CustomException.class
    })


    public ResponseEntity<ExceptionResponse> customRequestException(final CustomException CE) {
        log.warn("Exception: " +CE.getErrorCode());

        HttpStatus httpStatus = errorStatus(CE.getErrorCode());

        return ResponseEntity.status(httpStatus).body(new ExceptionResponse(CE.getMessage(), CE.getErrorCode()));
    }
    private HttpStatus errorStatus(ErrorCode errorCode) {
        switch (errorCode) {
            case ALREADY_REGISTER_USER:
                return HttpStatus.BAD_REQUEST;
            case NOT_FOUND_ID:
            case NOT_FOUND_PW:
            case NOT_FOUND_DIARY:
                return HttpStatus.NOT_FOUND;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

//    public ResponseEntity<ExceptionResponse> customRequestException(final CustomException CE) {
//        log.warn("Exception: " +CE.getErrorCode());
//
//        HttpStatus httpStatus = null;
//        if (CE.getErrorCode() == ErrorCode.ALREADY_REGISTER_USER) {
//            httpStatus = HttpStatus.BAD_REQUEST;
//        } else if (CE.getErrorCode() == ErrorCode.NOT_FOUND_ID) {
//            httpStatus = HttpStatus.NOT_FOUND;
//        } else if (CE.getErrorCode() == ErrorCode.NOT_FOUND_PW){
//            httpStatus = HttpStatus.NOT_FOUND;
//        }else if (CE.getErrorCode() == ErrorCode.SERVER_ERROR) {
//            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//        } else if (CE.getErrorCode() == ErrorCode.NOT_FOUND_DIARY) {
//            httpStatus = HttpStatus.NOT_FOUND;
//        }
//
//        return ResponseEntity.status(httpStatus).body(new ExceptionResponse(CE.getMessage(), CE.getErrorCode()));
//    }

//    public ResponseEntity<ExceptionResponse> customRequestException(final CustomException CE) {
//        log.warn("Exception: " +CE.getErrorCode()); // 예외발생했들 떄 로깅처리
//        return ResponseEntity.badRequest().body(new ExceptionResponse(CE.getMessage(), CE.getErrorCode()));
//    }

    @Getter
    @ToString
    @AllArgsConstructor
    public static class ExceptionResponse {
        private String msg;
        private ErrorCode errorCode;
    }

}
