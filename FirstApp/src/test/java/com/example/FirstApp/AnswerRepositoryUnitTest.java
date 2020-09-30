package com.example.FirstApp;



import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Exceptions.CustomExceptions.EntityNotFoundException;
import com.example.FirstApp.Repositories.AnswerRepository;
import com.example.FirstApp.Services.Interface.AnswerService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswerRepositoryUnitTest {

    @Autowired
    private AnswerService answerService;

    @MockBean
    private AnswerRepository answerRepository;

    @Test
    public void getAnswersTest() {
        //when
        when(answerRepository.findAll()).thenReturn(Stream
                .of(new Answer("odg",true),new Answer("drugi",false)).collect(Collectors.toList())    );

        // then
        assertEquals(2,answerService.getAllAnswers().size());
        assertEquals(false,answerService.getAllAnswers().get(1).getCorrect());
    }

    @Test(expected = EntityNotFoundException.class)
    public void getAnswersTestException()
    {
        when(answerRepository.findById(12L)).thenThrow(new EntityNotFoundException("Answer with id 12 doesn't exist"));

        answerService.getAnswerById(13L);
    }

    @Test
    public void getAnswerByIdTest()
    {
        when(answerRepository.findById(13L)).thenReturn(Optional.of(new Answer("Answer of the question", false)));

        assertEquals(  answerService.getAnswerById(13L).getText(),"Answer of the question"  );
    }




}
