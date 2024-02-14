package com.diary.security;


import com.diary.dto.UserLoginDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenProvider {

    @Value("${jwt.secret}") // yml 안에 secret
    private String secretKey;

    private static final long EXPRIATION_DATE = 1000 * 60 * 60; // 1시간

    public String generationToken(UserLoginDto userLoginDto) {
        Claims claims = Jwts.claims().setSubject(userLoginDto.getEmail());
        // 정보 한 조각(토큰에 담길 정보).subject(사용장에 대한 식별값) 에 담는다 (매개변수로 들어온 userLoginDto 의 Email)

        Date now = new Date();
        Date ExpireDate = new Date(now.getTime()+EXPRIATION_DATE);  // 1시간 뒤의 시간

        return Jwts.builder() // builder 를 사용해 생성
                .setClaims(claims)  // 토큰에 담길 정보
                .setIssuedAt(now)   // 발급시간
                .setExpiration(ExpireDate)  // 만료시간
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 알고리즘 대부분 이거 사용한다함.
                .compact();
    }
}
