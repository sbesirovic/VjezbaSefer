package com.example.FirstApp.Services.Interface;


import com.vjezba.DTO.AnswerRequestDto;
import com.vjezba.DTO.AnswerResponseDto;
import com.vjezba.DTO.QuestionRequestDto;
import com.vjezba.DTO.QuestionResponseDto;

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
