package com.diary.domain.repository;

import com.diary.domain.entity.Diary;
import com.diary.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary>findByUser(User userId);

    Optional<Diary>findByIdAndUser (Long id, User user);
}
