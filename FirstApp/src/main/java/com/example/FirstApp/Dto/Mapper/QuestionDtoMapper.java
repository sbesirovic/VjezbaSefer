package com.example.FirstApp.Dto.Mapper;


import com.example.FirstApp.Dto.QuestionRequestDto;
import com.example.FirstApp.Dto.QuestionResponseDto;
import com.example.FirstApp.Entities.Question;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


import java.util.List;

@Mapper(uses = {AnswerDtoMapper.class},componentModel = "spring")
public interface QuestionDtoMapper {

    //QuestionDtoMapper INSTANCE = Mappers.getMapper(QuestionDtoMapper.class);
        // mappers.getmapper se vise ne koristi vec @autowired zbog atributa componentModel = "spring"

    QuestionResponseDto questionToResponseQuestion(Question question);

    Question requestQuestionToQuestion(QuestionRequestDto questionRequestDto);

    List<QuestionResponseDto> questionListToResponseQuestionList ( List<Question> questionList);

}
