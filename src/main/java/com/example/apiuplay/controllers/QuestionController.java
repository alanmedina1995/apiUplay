package com.example.apiuplay.controllers;

import com.example.apiuplay.models.entities.Question;
import com.example.apiuplay.models.entities.User;
import com.example.apiuplay.services.QuestionService;
import com.example.apiuplay.services.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final UserService userService;

    public QuestionController(QuestionService questionService, UserService userService) {
        this.questionService = questionService;
        this.userService = userService;
    }

    @GetMapping(value = "getQuestions")
    public ResponseEntity<List> getQuestions() {
        HttpHeaders headers = new HttpHeaders();
        List<Question> questionList = questionService.getAll();
        headers.add("Header", "OK");
        return new ResponseEntity<>(questionList, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/getUserQuestion")
    public ResponseEntity<Question> getUserQuestion(@RequestBody String email) {
        HttpHeaders headers = new HttpHeaders();
        User user = userService.findByEmail(email);
        if(ObjectUtils.isNotEmpty(user)){
            Question userQuestion = questionService.getSelectedAsk(user.getId());
            headers.add("Header", "OK");
            return new ResponseEntity<>(userQuestion, headers, HttpStatus.OK);
        }
        headers.add("Header", "FAIL");
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }


}
