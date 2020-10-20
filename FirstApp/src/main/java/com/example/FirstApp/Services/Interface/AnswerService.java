package com.example.FirstApp.Services.Interface;

import com.example.FirstApp.Entities.Answer;
import com.vjezba.DTO.AnswerRequestDto;
import com.vjezba.DTO.AnswerResponseDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface AnswerService {
    List<AnswerResponseDto> getAllAnswers();
    AnswerResponseDto getAnswerById(ObjectId id);
    AnswerResponseDto addAnswer (AnswerRequestDto answer);
    Answer connectAnswerWithQuestion(ObjectId id, ObjectId idQuestion);
    void deleteAnswerById(ObjectId id);
}
