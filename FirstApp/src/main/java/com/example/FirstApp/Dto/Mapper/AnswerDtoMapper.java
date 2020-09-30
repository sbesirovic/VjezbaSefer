package com.example.FirstApp.Dto.Mapper;

import com.example.FirstApp.Dto.AnswerRequestDto;
import com.example.FirstApp.Dto.AnswerResponseDto;
import com.example.FirstApp.Entities.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerDtoMapper {

    //AnswerDtoMapper INSTANCE = Mappers.getMapper(AnswerDtoMapper.class);

    @Mappings({
            @Mapping(target = "testiramDtoExplicit", /*source = "answer.practice",/* defaultExpression="java(13)",*/expression = "java(answer.getId()+13L)"),
            @Mapping(target = "text",source = "answer.answerText")
    })
    AnswerResponseDto answerToResponseAnswer (Answer answer);

    @Mapping(target = "answerText",source = "answerRequestDto.text")
    Answer requestAnswerToAnswer (AnswerRequestDto answerRequestDto);


    List<AnswerResponseDto> answerListToAnswerResponseList(List<Answer> answerList);
}
