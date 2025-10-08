package com.onewordapp.controller;

import com.onewordapp.entity.Author;
import com.onewordapp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register") // Only AuthorController handles this
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        Author author = new Author();
        author.setUsername(username);
        author.setPassword(passwordEncoder.encode(password));
        authorRepository.save(author);
        return "redirect:/login"; // After registration, go to login
    }
}
