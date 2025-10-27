package com.onewordapp.controller;

import com.onewordapp.entity.Author;
import com.onewordapp.entity.OneWord;
import com.onewordapp.repository.AuthorRepository;
import com.onewordapp.repository.OneWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class WordController {

    @Autowired
    private OneWordRepository oneWordRepository;

    @Autowired
    private AuthorRepository authorRepository;

    // ❌ Removed the /words → community.html mapping
    // DashboardController already provides allWords for the toggle

    // ✅ Author profile
    @GetMapping("/author/{id}")
    public String viewAuthorWords(@PathVariable("id") Integer id, Model model) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isEmpty()) {
            return "redirect:/dashboard"; // safer redirect now
        }

        Author author = optionalAuthor.get();
        List<OneWord> authorWords = oneWordRepository.findAllByAuthorId(author.getId());
        model.addAttribute("author", author);
        model.addAttribute("authorWords", authorWords);
        return "author"; // src/main/resources/templates/author.html
    }
}