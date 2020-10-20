package com.example.FirstApp.Services.Interface;


import com.vjezba.DTO.AnswerRequestDto;
import com.vjezba.DTO.AnswerResponseDto;
import com.vjezba.DTO.QuestionRequestDto;
import com.vjezba.DTO.QuestionResponseDto;
import org.bson.types.ObjectId;

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

}
