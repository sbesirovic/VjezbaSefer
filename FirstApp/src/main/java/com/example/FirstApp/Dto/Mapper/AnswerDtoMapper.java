package com.example.FirstApp.Dto.Mapper;

import com.example.FirstApp.Entities.Answer;
import com.vjezba.DTO.AnswerRequestDto;
import com.vjezba.DTO.AnswerResponseDto;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerDtoMapper {

    //AnswerDtoMapper INSTANCE = Mappers.getMapper(AnswerDtoMapper.class);

    @Mappings({
            @Mapping(target = "testiramDtoExplicit", source = "answer.practice",defaultExpression="java(-2L)"/*expression = "java(answer.getId()+13L)"*/),
            @Mapping(target = "text",source = "answer.answerText"),
            @Mapping(target = "id", source = "answer.id",defaultExpression="java("+"String.valueOf(-2L)"+")")//, ubacena radi unit testova da ne krahira kad nema id
    })
    AnswerResponseDto answerToResponseAnswer (Answer answer);

    @Mapping(target = "answerText",source = "answerRequestDto.text")
    Answer requestAnswerToAnswer (AnswerRequestDto answerRequestDto);


    List<AnswerResponseDto> answerListToAnswerResponseList(List<Answer> answerList);
}
