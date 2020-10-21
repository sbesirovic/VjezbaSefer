package com.example.FirstApp.Dto.Mapper;

import com.example.FirstApp.Dto.Mapper.MyPractice.Firstelement;
import com.example.FirstApp.Dto.Mapper.MyPractice.helperClass;
import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Entities.Question;
import com.vjezba.DTO.AnswerRequestDto;
import com.vjezba.DTO.AnswerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Mapper(componentModel = "spring",uses = {helperClass.class})
public interface AnswerDtoMapper {

    //AnswerDtoMapper INSTANCE = Mappers.getMapper(AnswerDtoMapper.class);
@Autowired
helperClass helper = null;

    @Mappings({
            @Mapping(target = "testiramDtoExplicit", source = "answer.practice",defaultExpression="java(-2L)"/*expression = "java(answer.getId()+13L)"*/),
            @Mapping(target = "text",source = "answer.answerText"),
            @Mapping(target = "id", source = "answer.id",defaultExpression="java("+"String.valueOf(-2L)"+")")//, ubacena radi unit testova da ne krahira kad nema id
    })
    AnswerResponseDto answerToResponseAnswer (Answer answer);

    @Mapping(target = "answerText",source = "answerRequestDto.text")
    Answer requestAnswerToAnswer (AnswerRequestDto answerRequestDto);


    List<AnswerResponseDto> answerListToAnswerResponseList(List<Answer> answerList);

    /*
        @Mapping( source = "answers", target = "answers",qualifiedBy = Firstelement.class)
        TestClass QuestionToListAnswers(Question question );  iterable non iterable problems
     */


    @Mapping( source = "answers", target = "answers")
    QuestionAnswersDto QuestionToListAnswers(Question question );


}


