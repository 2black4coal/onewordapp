package com.onewordapp.controller;

import com.onewordapp.entity.Author;
import com.onewordapp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // ✅ import Model
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Registration page
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("author", new Author()); // ✅ provides empty Author for form binding
        return "register"; // src/main/resources/templates/register.html
    }

    // Process registration using @ModelAttribute
    @PostMapping("/register")
    public String register(@ModelAttribute Author author) {
        if (authorRepository.existsByUsername(author.getUsername())) {
            return "redirect:/register?error=username-taken";
        }

        String encodedPassword = passwordEncoder.encode(author.getPassword());
        author.setPassword(encodedPassword);
        authorRepository.save(author);

        return "redirect:/login";
    }
}