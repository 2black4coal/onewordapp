package com.onewordapp.controller;

import com.onewordapp.entity.Author;
import com.onewordapp.entity.OneWord;
import com.onewordapp.repository.AuthorRepository;
import com.onewordapp.repository.OneWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class WordController {

    @Autowired
    private OneWordRepository oneWordRepository;

    @Autowired
    private AuthorRepository authorRepository;

    // Post a word
    @PostMapping("/post-word")
    public String postWord(@RequestParam("text") String text, Principal principal) {
        Optional<Author> optionalAuthor = authorRepository.findByUsername(principal.getName());
        if (optionalAuthor.isEmpty()) {
            return "redirect:/login";
        }

        Author author = optionalAuthor.get();
        OneWord word = new OneWord();
        word.setContent(text);
        word.setAuthor(author);
        word.setTimestamp(LocalDateTime.now());
        oneWordRepository.save(word);

        return "redirect:/dashboard";
    }

    // Community page
    @GetMapping("/words")
    public String viewAllWords(Model model) {
        List<OneWord> allWords = oneWordRepository.findAll();
        model.addAttribute("allWords", allWords);
        return "community"; // src/main/resources/templates/community.html
    }

    // Author profile
    @GetMapping("/author/{id}")
    public String viewAuthorWords(@PathVariable("id") Integer id, Model model) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isEmpty()) {
            return "redirect:/words";
        }

        Author author = optionalAuthor.get();
        List<OneWord> authorWords = oneWordRepository.findAllByAuthorId(author.getId());
        model.addAttribute("author", author);
        model.addAttribute("authorWords", authorWords);
        return "author"; // src/main/resources/templates/author.html
    }
}