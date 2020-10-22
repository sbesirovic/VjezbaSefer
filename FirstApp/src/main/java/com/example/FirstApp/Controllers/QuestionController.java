package com.example.FirstApp.Controllers;

import com.example.FirstApp.Dto.Mapper.QuestionAnswersDto;
import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Entities.Question;
import com.example.FirstApp.OnCreate;
import com.example.FirstApp.OnUpdate;
import com.example.FirstApp.Services.Interface.QuestionService;
import com.vjezba.DTO.AnswerRequestDto;
import com.vjezba.DTO.AnswerResponseDto;
import com.vjezba.DTO.QuestionRequestDto;
import com.vjezba.DTO.QuestionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController                             //@Controler nije radilo pa ovo staviom, vidi razlike
@RequestMapping (value = "/questions")
@Validated
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    //@ApiOperation(value = "", authorizations = { @Authorization(value="Bearer") })   ako ne postavim security context u swaggerConfig klasi
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public  List<QuestionResponseDto> getAllQuestions ()
    {
        return questionService.getAllQuestions();
    }


    @GetMapping (path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public QuestionResponseDto getQuestionById(@PathVariable(value = "id") String id)
    {
        return questionService.getQuestionById(id);
    }

    @GetMapping (path = "/{id}/answers/{idAnswer}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AnswerResponseDto getAnswerByIdFromQuestionId(@PathVariable(value = "id") String id, @PathVariable(value = "idAnswer") String idAnswer)
    {
        return questionService.getAnswerByIdFromQuestionById(id,idAnswer);
    }

    @GetMapping (path = "/{id}/answers")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public QuestionAnswersDto getAllAnswerByQuestionId(@PathVariable(value = "id") String id)
    {
        return questionService.getAllAnswersByQuestionId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Validated(OnCreate.class)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public QuestionResponseDto addQuestion (@Valid @RequestBody QuestionRequestDto questionRequestDto)
    {
        return questionService.addQuestion(questionRequestDto);
    }

    @PostMapping (value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Validated(OnCreate.class)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AnswerResponseDto addAnswer (@PathVariable(value = "id") String id, @Valid @RequestBody AnswerRequestDto answerRequestDto)
    {
        return questionService.addAnswer(id,answerRequestDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void  deleteAllQuestions ()
    {
         questionService.deleteAllQuestion();
    }

    @DeleteMapping (path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void  deleteQuestionById (@PathVariable(value = "id") String id)
    {
        questionService.deleteQuestionById(id);
    }

    @DeleteMapping(path = "/{id}/answers/{idAnswer}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteQuestionAnswerById (@PathVariable(value = "id") String id,@PathVariable(value = "idAnswer") String idAnswer)
    {
        questionService.deleteQuestionAnswerByIdAnswer(id,idAnswer);
    }

    @PutMapping (path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Validated(OnUpdate.class)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public QuestionResponseDto editQuestionById(@PathVariable(value = "id") String id, @Valid @RequestBody QuestionRequestDto questionRequestDto)
    {
        return questionService.editQuestionById(id, questionRequestDto);
    }


}
