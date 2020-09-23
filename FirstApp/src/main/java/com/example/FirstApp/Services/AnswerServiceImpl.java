package com.example.FirstApp.Services;

import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Entities.Question;
import com.example.FirstApp.Repositories.AnswerRepository;
import com.example.FirstApp.Repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    private AnswerRepository answerRepository;

    //$###########################
    @Autowired
    private QuestionRepository questionRepository; // dodano
    //###############################

    @Override
    public List<Answer> getAllAnswers() {
        return (List<Answer>) answerRepository.findAll();
    }

    @Override
    public Answer getAnswerById(Long id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if(optionalAnswer.isPresent()) return optionalAnswer.get();
        else return null;
    }

    @Override
    public Answer addAnswer(Answer answer) {
        // uslovi body da nisu nulovi ?
        return answerRepository.save(answer);
    }
// dto unit i integracioni
    //#########################
    @Override
    public Answer connectAnswerWithQuestion(Long id, Long idQuestion) {
    Answer answer = answerRepository.findById(id).get();
    answer.setQuestion(questionRepository.findById(idQuestion).get());
    return answerRepository.save(answer);
    }
    //###########################3
}
