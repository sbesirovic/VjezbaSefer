package com.example.FirstApp.Controllers;

import com.example.FirstApp.Dto.AnswerRequestDto;
import com.example.FirstApp.Dto.AnswerResponseDto;
import com.example.FirstApp.Dto.QuestionRequestDto;
import com.example.FirstApp.Dto.QuestionResponseDto;
import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Entities.Question;
import com.example.FirstApp.OnCreate;
import com.example.FirstApp.OnUpdate;
import com.example.FirstApp.Services.Interface.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


@RestController                             //@Controler nije radilo pa ovo staviom, vidi razlike
@RequestMapping (value = "/questions")
@Validated
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public  List<QuestionResponseDto> getAllQuestions ()
    {
        return questionService.getAllQuestions();
    }

    @GetMapping (path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public QuestionResponseDto getQuestionById(@PathVariable(value = "id") Long id)
    {
        return questionService.getQuestionById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Validated(OnCreate.class)
    public QuestionResponseDto addQuestion (@Valid @RequestBody QuestionRequestDto questionRequestDto)
    {
        return questionService.addQuestion(questionRequestDto);
    }

    @PostMapping (value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Validated(OnCreate.class)
    public AnswerResponseDto addAnswer (@PathVariable(value = "id") Long id, @Valid @RequestBody AnswerRequestDto answerRequestDto)
    {
        return questionService.addAnswer(id,answerRequestDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deleteAllQuestions ()
    {
         questionService.deleteAllQuestion();
    }

    @DeleteMapping (path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deleteQuestionById (@PathVariable(value = "id") Long id)
    {
        questionService.deleteQuestionById(id);
    }

    @DeleteMapping(path = "/{id}/answers/{idAnswer}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestionAnswerById (@PathVariable(value = "id") Long id,@PathVariable(value = "idAnswer") Long idAnswer)
    {
        questionService.deleteQuestionAnswerByIdAnswer(id,idAnswer);
    }

    @PutMapping (path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Validated(OnUpdate.class)
    public QuestionResponseDto editQuestionById(@PathVariable(value = "id") Long id, @Valid @RequestBody QuestionRequestDto questionRequestDto)
    {
        return questionService.editQuestionById(id, questionRequestDto);
    }





}
