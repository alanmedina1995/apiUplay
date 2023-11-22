package com.example.apiuplay.models.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "question_Xuser")
public class QuestionXUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private Question question;

    private String answer;

    public QuestionXUser(User user, Question question, String answer) {
        this.user = user;
        this.question = question;
        this.answer = answer;
    }

    public QuestionXUser() {

    }

}
