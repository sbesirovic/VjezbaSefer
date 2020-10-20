package com.example.FirstApp.Services.Interface;

import com.example.FirstApp.Entities.Answer;
import com.vjezba.DTO.AnswerRequestDto;
import com.vjezba.DTO.AnswerResponseDto;

import java.util.List;

public interface AnswerService {
    List<AnswerResponseDto> getAllAnswers();
    AnswerResponseDto getAnswerById(String id);
    AnswerResponseDto addAnswer (AnswerRequestDto answer);
    Answer connectAnswerWithQuestion(String id, String idQuestion);
    void deleteAnswerById(String id);
}
