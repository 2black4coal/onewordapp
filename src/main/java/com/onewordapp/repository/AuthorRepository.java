package com.onewordapp.repository;

import com.onewordapp.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    boolean existsByUsername(String username);

    Optional<Author> findByUsername(String username);
}