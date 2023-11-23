package com.example.apiuplay.services;

import com.example.apiuplay.models.entities.Question;
import com.example.apiuplay.models.entities.QuestionXUser;
import com.example.apiuplay.repository.QuestionRepository;
import com.example.apiuplay.repository.QuestionXUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionXUserRepository questionXUserRepository;

    public QuestionService(QuestionRepository questionRepository, QuestionXUserRepository questionXUserRepository) {
        this.questionRepository = questionRepository;
        this.questionXUserRepository = questionXUserRepository;
    }

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public Question getSelectedAsk(Long userId) {
        QuestionXUser questionXUser = questionXUserRepository.findByUserId(userId);
        return questionRepository.findById(questionXUser.getQuestion().getId()).orElse(null);
    }
}
