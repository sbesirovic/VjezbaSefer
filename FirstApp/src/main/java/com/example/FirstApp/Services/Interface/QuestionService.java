package com.example.FirstApp.Services.Interface;


import com.example.FirstApp.Dto.Mapper.QuestionAnswersDto;
import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Entities.Question;
import com.vjezba.DTO.AnswerRequestDto;
import com.vjezba.DTO.AnswerResponseDto;
import com.vjezba.DTO.QuestionRequestDto;
import com.vjezba.DTO.QuestionResponseDto;

import java.util.List;

public interface QuestionService {

    List<QuestionResponseDto> getAllQuestions();
    QuestionResponseDto addQuestion (QuestionRequestDto questionRequestDto);
    void deleteAllQuestion();
    QuestionResponseDto editQuestionById(String id, QuestionRequestDto questionRequestDto);
    QuestionResponseDto getQuestionById(String id);
    void deleteQuestionById (String id);
    AnswerResponseDto addAnswer (String id, AnswerRequestDto answerRequestDto);
    void deleteQuestionAnswerByIdAnswer (String id, String idAnswer);
    AnswerResponseDto getAnswerByIdFromQuestionById (String id, String idAnswer);


    QuestionAnswersDto getAllAnswersByQuestionId (String id);



}
