package com.example.apiuplay.repository;

import com.example.apiuplay.models.entities.QuestionXUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionXUserRepository extends JpaRepository<QuestionXUser, Long> {

    QuestionXUser findByUserId(Long userId);
}
