package com.diary.service;

import com.diary.domain.entity.User;
import com.diary.domain.repository.UserRepository;
import com.diary.dto.UserLoginDto;
import com.diary.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;  // @Bean 을 등록해야 사용 가능

    public String join(UserInfoDto userJoinRequestDto) {     // String 탑입의 join 은( 매개변수로 userJoinRequestDto 를 받는다)
        if(userRepository.findByEmail(userJoinRequestDto.getEmail()).isPresent()) { //만약(userRepository 안에 finByEmail(매개변수로 들어온 값의 email) 이 같다면)
            throw new RuntimeException("이미 존재하는 아이디 입니다."); // 예외발생
        }
        userRepository.save(    // jpa 의 기본기능 save  ?여기에 변수명 하는 이유 물어보기
                User.builder()  // 객체생성
                        .email(userJoinRequestDto.getEmail())   // email 은 매개변수로 들어온 email
                        .password(passwordEncoder.encode(userJoinRequestDto.getPassword()))
                        .name(userJoinRequestDto.getName())
                        .phone(userJoinRequestDto.getPhone())
                        .build());

        return "회원가입 완료!";
    }

    public String login(UserLoginDto userLoginDto) {
        Optional<User> loginUser = userRepository.findByEmail(userLoginDto.getEmail());
        if(!loginUser.isPresent()) {
            throw new RuntimeException("존재하지 않는 아이디 입니다."); // 예외발생
        }
        if(!passwordEncoder.matches(userLoginDto.getPassword(), loginUser.get().getPassword())) {   // passwordEncoder.matches(암호화안된비번, 암호화된비번); 값을 비교해줌
            throw new RuntimeException("비밀번호가 틀립니다.");
        }

        return "로그인 성공!";
    }

}
