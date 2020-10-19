package com.example.FirstApp.QuestionTest;

import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Entities.Question;
import com.example.FirstApp.FirstAppApplication;
import com.example.FirstApp.Repositories.AnswerRepository;
import com.example.FirstApp.Repositories.QuestionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FirstAppApplication.class)
public class QuestionRepositoryIntegrationTest {


    @Autowired
    private QuestionRepository questionRepository;

    @Test
    @Sql( {"/schema.sql","/data.sql"})
    public void testQuestionRepositoryFindAll()
    {
        List<Question> list = (List<Question>) questionRepository.findAll();

        for(Question question:list) System.out.println(question);

        assertEquals(3,list.size());
    }



}
