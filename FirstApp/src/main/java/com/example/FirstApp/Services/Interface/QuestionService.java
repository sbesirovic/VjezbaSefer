package com.example.FirstApp.Services.Interface;


import com.example.FirstApp.Dto.AnswerRequestDto;
import com.example.FirstApp.Dto.AnswerResponseDto;
import com.example.FirstApp.Dto.QuestionRequestDto;
import com.example.FirstApp.Dto.QuestionResponseDto;
import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Entities.Question;

import java.util.List;

public interface QuestionService {
    List<QuestionResponseDto> getAllQuestions();
    QuestionResponseDto addQuestion (QuestionRequestDto questionRequestDto);
    void deleteAllQuestion();
    QuestionResponseDto editQuestionById(Long id, QuestionRequestDto questionRequestDto);
    QuestionResponseDto getQuestionById(Long id);
    void deleteQuestionById (Long id);
    AnswerResponseDto addAnswer (Long id, AnswerRequestDto answerRequestDto);
    void deleteQuestionAnswerByIdAnswer (Long id, Long idAnswer);

}
