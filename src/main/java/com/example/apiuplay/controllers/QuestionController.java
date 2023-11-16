package com.example.apiuplay.controllers;

import com.example.apiuplay.models.entities.Question;
import com.example.apiuplay.services.QuestionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/asks")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(value = "getQuestions")
    public ResponseEntity<List> getQuestions() {
        HttpHeaders headers = new HttpHeaders();
        List<Question> questionList = questionService.getAll();
        headers.add("Header", "OK");
        return new ResponseEntity<>(questionList, headers, HttpStatus.OK);
    }

    @PostMapping(value = "getUserQuestion")
    public ResponseEntity<Question> getUserQuestion(@RequestBody Long userId) {
        HttpHeaders headers = new HttpHeaders();
        Question userQuestion = questionService.getSelectedAsk(userId);
        headers.add("Header", "OK");
        return new ResponseEntity<>(userQuestion, headers, HttpStatus.OK);
    }


}
