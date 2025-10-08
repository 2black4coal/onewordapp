package com.onewordapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // Home page
    @GetMapping("/")
    public String index() {
        return "index"; // src/main/resources/templates/index.html
    }

    // About page
    @GetMapping("/about")
    public String about() {
        return "about"; // src/main/resources/templates/about.html
    }

    // Contact page
    @GetMapping("/contact")
    public String contact() {
        return "contact"; // src/main/resources/templates/contact.html
    }

    // Add more pages as needed
}
