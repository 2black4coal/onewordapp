package com.onewordapp.service;

import com.onewordapp.entity.Author;
import com.onewordapp.repository.AuthorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author registerAuthor(String username, String password) {
        if (authorRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        Author author = new Author();
        author.setUsername(username);
        author.setPassword(passwordEncoder.encode(password));
        return authorRepository.save(author);
    }

    public Optional<Author> findByUsername(String username) {
        return authorRepository.findByUsername(username);
    }
}