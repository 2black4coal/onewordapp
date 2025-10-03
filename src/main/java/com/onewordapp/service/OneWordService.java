package com.onewordapp.service;

import com.onewordapp.entity.OneWord;
import com.onewordapp.repository.OneWordRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OneWordService {

    private final OneWordRepository oneWordRepository;

    public OneWordService(OneWordRepository oneWordRepository) {
        this.oneWordRepository = oneWordRepository;
    }

    public OneWord postWord(OneWord word) {
        return oneWordRepository.save(word);
    }

    public List<OneWord> getAllWords() {
        return oneWordRepository.findAll();
    }
}