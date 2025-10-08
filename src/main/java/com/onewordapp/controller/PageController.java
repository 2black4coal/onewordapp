package com.onewordapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // Home page
    @GetMapping({ "/", "/index" })
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

    // Post-word page
    @GetMapping("/post-word")
    public String postWord() {
        return "post-word"; // src/main/resources/templates/post-word.html
    }
}
