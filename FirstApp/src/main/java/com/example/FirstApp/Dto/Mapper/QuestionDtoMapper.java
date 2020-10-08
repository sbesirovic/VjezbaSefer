package com.example.FirstApp.Dto.Mapper;


import com.example.FirstApp.Entities.Question;
import com.vjezba.DTO.QuestionRequestDto;
import com.vjezba.DTO.QuestionResponseDto;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(uses = {AnswerDtoMapper.class},componentModel = "spring")
public interface QuestionDtoMapper {

    //QuestionDtoMapper INSTANCE = Mappers.getMapper(QuestionDtoMapper.class);
        // mappers.getmapper se vise ne koristi vec @autowired zbog atributa componentModel = "spring"

    QuestionResponseDto questionToResponseQuestion(Question question);

    Question requestQuestionToQuestion(QuestionRequestDto questionRequestDto);

    List<QuestionResponseDto> questionListToResponseQuestionList ( List<Question> questionList);

}
