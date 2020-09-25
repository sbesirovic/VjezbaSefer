package com.example.FirstApp.Services.Interface;

import com.example.FirstApp.Dto.AnswerRequestDto;
import com.example.FirstApp.Dto.AnswerResponseDto;
import com.example.FirstApp.Entities.Answer;

import java.util.List;

public interface AnswerService {
    List<AnswerResponseDto> getAllAnswers();
    AnswerResponseDto getAnswerById(Long id);
    AnswerResponseDto addAnswer (AnswerRequestDto answer);
    Answer connectAnswerWithQuestion(Long id, Long idQuestion);
}
