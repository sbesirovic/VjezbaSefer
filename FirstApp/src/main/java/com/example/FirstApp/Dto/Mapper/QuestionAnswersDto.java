package com.example.FirstApp.Dto.Mapper;

import com.vjezba.DTO.AnswerResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor
public class QuestionAnswersDto {

    private List<AnswerResponseDto> answers;


    public AnswerResponseDto getAnswerById(String idAnswer) // jel smijem ? nema mi logike maper za ovu stvar izvlacenje po id
    {
        for(AnswerResponseDto answerResponseDto : answers)
        {
            if(answerResponseDto.getId().equals(idAnswer)) return answerResponseDto;
        }
        return null;
    }
}
