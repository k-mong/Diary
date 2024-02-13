package com.diary.domain.repository;

import com.diary.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail (String email);  // Optional 은 값이 존재할 수도 있고 존재하지 않을수 있을때 사용
}
