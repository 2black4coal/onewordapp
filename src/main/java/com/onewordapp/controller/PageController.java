package com.onewordapp.controller;

import com.onewordapp.repository.OneWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Autowired
    private OneWordRepository oneWordRepository;

    // Home page
    @GetMapping({ "/", "/home", "/index" })
    public String index() {
        return "index"; // src/main/resources/templates/index.html
    }

    // Login page
    @GetMapping("/login")
    public String login() {
        return "login"; // src/main/resources/templates/login.html
    }

    // Register page
    @GetMapping("/register")
    public String register() {
        return "register"; // src/main/resources/templates/register.html
    }

    // Community feed
    @GetMapping("/words")
    public String community(Model model) {
        model.addAttribute("allWords", oneWordRepository.findAll());
        return "community"; // src/main/resources/templates/community.html
    }

    // Author profile page
    @GetMapping("/author")
    public String authorPage() {
        return "author"; // src/main/resources/templates/author.html
    }
}