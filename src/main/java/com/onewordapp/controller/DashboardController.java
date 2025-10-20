package com.onewordapp.controller;

import com.onewordapp.entity.Author;
import com.onewordapp.entity.OneWord;
import com.onewordapp.repository.AuthorRepository;
import com.onewordapp.repository.OneWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private OneWordRepository oneWordRepository;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) {
        Author author = authorRepository.findByUsername(principal.getName())
                .orElse(null);

        if (author == null) {
            return "redirect:/login";
        }

        // Always provide non-null lists to Thymeleaf
        List<OneWord> myWords = oneWordRepository.findAllByAuthorId(author.getId());
        List<OneWord> allWords = oneWordRepository.findAll();

        model.addAttribute("author", author);
        model.addAttribute("myWords", myWords != null ? myWords : Collections.emptyList());
        model.addAttribute("allWords", allWords != null ? allWords : Collections.emptyList());

        return "dashboard"; // src/main/resources/templates/dashboard.html
    }

    @PostMapping("/post-word")
    public String postWord(@RequestParam("content") String content, Principal principal) {
        Author author = authorRepository.findByUsername(principal.getName())
                .orElse(null);

        if (author == null) {
            return "redirect:/login";
        }

        OneWord word = new OneWord();
        word.setContent(content); // ✅ matches entity field
        word.setAuthor(author);
        oneWordRepository.save(word);

        return "redirect:/dashboard";
    }
}