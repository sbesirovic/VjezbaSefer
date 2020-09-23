package com.example.FirstApp.Services;


import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Entities.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions();
    Question addQuestion (Question question);
    void deleteAllQuestion();
    Question editQuestionById(Long id, Question question);
    Question getQuestionById(Long id);
    void deleteQuestionById (Long id);
    Question addAnswer (Long id, Answer answer);

}
