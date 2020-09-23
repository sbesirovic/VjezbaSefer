package com.example.FirstApp.Services;

import com.example.FirstApp.Entities.Answer;

import java.util.List;

public interface AnswerService {
    List<Answer> getAllAnswers();
    Answer getAnswerById(Long id);
    Answer addAnswer (Answer answer);
    Answer connectAnswerWithQuestion(Long id, Long idQuestion);
}
