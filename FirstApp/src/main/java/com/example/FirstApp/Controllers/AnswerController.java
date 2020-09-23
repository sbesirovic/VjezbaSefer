package com.example.FirstApp.Controllers;

import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnswerController {

    private AnswerService answerService;

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    @RequestMapping(value = "/answers", method = RequestMethod.GET)
    public List<Answer> getAllAnswers()
    {
        return answerService.getAllAnswers();
    }

    @RequestMapping(value = "/answers/{id}", method = RequestMethod.GET)
    public Answer getAnswerById(@RequestParam(value = "id") Long id)
    {
        return answerService.getAnswerById(id);
    }

    @RequestMapping(value = "/answers", method = RequestMethod.POST)
    public Answer addAnswer(@RequestBody Answer answer)
    {
        return answerService.addAnswer(answer);
    }

    //$##################
    @RequestMapping(value = "/answers/{id}/questions/{idQuestion}", method = RequestMethod.PUT)
    public Answer connectAnswer(@PathVariable(value = "id") Long id,@PathVariable(value = "idQuestion") Long idQuestion)
    {
        return answerService.connectAnswerWithQuestion(id, idQuestion);
    }
    //###########

}
