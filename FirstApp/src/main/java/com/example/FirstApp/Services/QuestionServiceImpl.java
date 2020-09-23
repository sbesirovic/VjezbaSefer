package com.example.FirstApp.Services;

import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Entities.Question;
import com.example.FirstApp.Repositories.AnswerRepository;
import com.example.FirstApp.Repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements  QuestionService{

    private QuestionRepository questionRepository;

    @Autowired
    public void setQuestionRepository( QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getAllQuestions() {
        return (List<Question>) questionRepository.findAll();
    }

    @Override
    public Question addQuestion(Question question) {
        // posebni uslov za level < 1 , kakva greska da se desi ?
        if(question.getLevel()!=null && question.getQuestionText()!=null) return questionRepository.save(question);
        else return null; // odnosno neka greska ili nesto
    }

    @Override
    public void deleteAllQuestion() {
        questionRepository.deleteAll();
    }


    @Override
    public Question editQuestionById(Long id,Question question) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if(optionalQuestion.isPresent())
        {
            if(question.getLevel()!=null)optionalQuestion.get().setLevel(question.getLevel());
            if(question.getQuestionText()!=null)optionalQuestion.get().setQuestionText(question.getQuestionText());
            return questionRepository.save(optionalQuestion.get());
        }
        else return null; // neka greska da put ne moze mjenjati jer ne postoji taj id
    }

    @Override
    public Question getQuestionById(Long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if(optionalQuestion.isPresent())  return optionalQuestion.get();
        else return null;
    }

    @Override
    public void deleteQuestionById(Long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if(optionalQuestion.isPresent()) questionRepository.deleteById(id);
        // else neki error da nije void
    }

    @Override
    public Question addAnswer(Long id, Answer answer) {

        Question question = questionRepository.findById(id).get();
        if(question == null) System.out.println("samo da vidim jel ovo bude");
        else
        {
            question.setAnswer(answer);
            answer.setQuestion(question); // DA LI JE OVA LINIJA BITNA? % ASK

            return questionRepository.save(question);
        }
        return question;
    }
}
