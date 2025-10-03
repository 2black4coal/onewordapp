package com.onewordapp.repository;

import com.onewordapp.entity.OneWord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OneWordRepository extends JpaRepository<OneWord, Integer> {
    List<OneWord> findAllByAuthorId(Integer authorId);
}