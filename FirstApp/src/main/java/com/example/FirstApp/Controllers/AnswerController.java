package com.example.FirstApp.Controllers;

import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Services.Interface.AnswerService;
import com.vjezba.DTO.AnswerRequestDto;
import com.vjezba.DTO.AnswerResponseDto;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // = @Controller + @ResponseBody on all methods (body = return value) || vol2 : response entity
@RequestMapping(path = "/answers")
public class AnswerController {


    @Autowired
    private AnswerService answerService;

    /*public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;                             izbaceno jer imas autowired
    }*/

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<AnswerResponseDto> getAllAnswers()
    {
        return answerService.getAllAnswers();
    }


    @GetMapping(path="/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AnswerResponseDto getAnswerById(@PathVariable(value = "id") ObjectId id)
    {
        return answerService.getAnswerById(id);
    }

    //$##################
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnswerResponseDto addAnswer(@RequestBody AnswerRequestDto answerRequestDto)
    {
        return answerService.addAnswer(answerRequestDto);
    }

    @PutMapping(path = "/{id}/questions/{idQuestion}")
    @ResponseStatus(HttpStatus.OK)
    public Answer connectAnswer(@PathVariable(value = "id") ObjectId id, @PathVariable(value = "idQuestion") ObjectId idQuestion)
    {
        return answerService.connectAnswerWithQuestion(id, idQuestion);
    }
    //###########

    @DeleteMapping (path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void  deleteAnswerById (@PathVariable(value = "id") ObjectId id)
    {
        answerService.deleteAnswerById(id);
    }


}
