package com.example.FirstApp;

import com.example.FirstApp.Dto.AnswerResponseDto;
import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Entities.Question;
import com.example.FirstApp.Repositories.AnswerRepository;
import com.example.FirstApp.Repositories.QuestionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FirstAppApplication.class)
//@WebAppConfiguration // radi i bez ove stvari, tako da treba provjerit za sta je ovo tacno
public class AnswerRepositoryIntegrationTest {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void testQuestion()
    {
        List<Question> list = (List<Question>) questionRepository.findAll();
        for(Question question:list) System.out.println(question);

        System.out.println(list.size());
    }


    @Test
    public void testTest()
    {
        List<Answer> list = (List<Answer>) answerRepository.findAll();
        for(Answer answer:list) System.out.println(answer);
    }

}
