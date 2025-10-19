package com.onewordapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

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

    // Author profile page (static navigation entry point)
    @GetMapping("/author")
    public String authorPage() {
        return "author"; // src/main/resources/templates/author.html
    }
}