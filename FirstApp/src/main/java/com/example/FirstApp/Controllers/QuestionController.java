package com.example.FirstApp.Controllers;

import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Entities.Question;
import com.example.FirstApp.Services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController //@Controler nije radilo pa ovo staviom, vidi razlike

public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService)
    {
        this.questionService = questionService;
    }

    @RequestMapping (value = "/questions", method = RequestMethod.GET)
    public List<Question> getAllQuestions ()
    {
        return questionService.getAllQuestions();
    }

    @RequestMapping (value = "/questions/{id}", method = RequestMethod.GET)
    public Question getQuestionById(@PathVariable(value = "id") Long id)
    {
        return questionService.getQuestionById(id);
    }

    @RequestMapping (value = "/questions", method = RequestMethod.POST)
    public Question addQuestion (@RequestBody Question question)
    {
        return questionService.addQuestion(question);
    }

    @RequestMapping (value = "/questions", method = RequestMethod.DELETE)
    public void  deleteAllQuestions ()
    {
         questionService.deleteAllQuestion();
    }

    @RequestMapping (value = "/questions/{id}", method = RequestMethod.DELETE)
    public void  deleteQuestionById (@PathVariable(value = "id") Long id)
    {
        questionService.deleteQuestionById(id);
    }


    @RequestMapping (value = "/questions/{id}", method = RequestMethod.PUT)
    public Question editQuestionById(@PathVariable(value = "id") Long id, @RequestBody Question question)
    {
        return questionService.editQuestionById(id, question);
    }

    @RequestMapping (value = "/questions/{id}", method = RequestMethod.POST)
    public Question addAnswer (@PathVariable(value = "id") Long id, @RequestBody Answer answer)
    {
        return questionService.addAnswer(id,answer);
    }



}
