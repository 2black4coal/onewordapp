package com.onewordapp.controller;

import com.onewordapp.entity.Author;
import com.onewordapp.entity.OneWord;
import com.onewordapp.repository.AuthorRepository;
import com.onewordapp.service.OneWordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@Controller
public class WordController {

    private final OneWordService oneWordService;
    private final AuthorRepository authorRepository;

    public WordController(OneWordService oneWordService, AuthorRepository authorRepository) {
        this.oneWordService = oneWordService;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/post-word")
    public String postPage(@RequestParam Integer authorId, Model model) {
        Author author = authorRepository.findById(authorId).orElseThrow();
        model.addAttribute("author", author.getUsername());
        model.addAttribute("timestamp",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(java.time.LocalDateTime.now()));
        return "post-word";
    }

    @PostMapping("/post-word")
    public String postWord(@RequestParam String word, @RequestParam Integer authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow();
        OneWord oneWord = new OneWord();
        oneWord.setContent(word);
        oneWord.setAuthor(author);
        oneWordService.postWord(oneWord);
        return "redirect:/post-word?authorId=" + authorId;
    }
}