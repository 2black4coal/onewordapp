package com.onewordapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/") // Home / index page
    public String index() {
        return "index";
    }

    @GetMapping("/login") // Login page
    public String login() {
        return "login";
    }

    @GetMapping("/post-word") // Post Word page
    public String postWord() {
        return "post-word";
    }
}
