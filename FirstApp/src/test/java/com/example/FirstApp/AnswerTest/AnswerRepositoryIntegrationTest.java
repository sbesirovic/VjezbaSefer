package com.example.FirstApp.AnswerTest;

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
//@WebAppConfiguration // radi i bez ove stvari, tako da treba provjerit za sta je ovo tacno
//@ContextConfiguration(classes = FirstAppApplicationTests.class)
//@Sql({"/employees_schema.sql", "/import_employees.sql"})

@Sql("/schema.sql")
public class AnswerRepositoryIntegrationTest {

    @Autowired
    private AnswerRepository answerRepository;


    @Test
    @Sql( {"/schema.sql","/data.sql"})
    public void testAnswerRepositoryFindAll()
    {
        List<Answer> list = (List<Answer>) answerRepository.findAll();

        for(Answer answer:list) System.out.println(answer.toString());

        assertEquals(1,list.size());
    }



}
