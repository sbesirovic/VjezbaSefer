package com.example.FirstApp.QuestionTest;



import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Entities.Question;
import com.example.FirstApp.Exceptions.CustomExceptions.EntityNotFoundException;
import com.example.FirstApp.FirstAppApplication;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.FirstApp.Repositories.QuestionRepository;
import com.example.FirstApp.Services.Interface.QuestionService;
import com.vjezba.DTO.AnswerResponseDto;
import com.vjezba.DTO.QuestionResponseDto;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)  // poenta je sto sad moze i bez mocka zvat repo nad DB, pa nez jel bas to uredu da bude kod unit testova ? 100% warning. Jedino da mockujem dtoMapper sa onim nacinom before() u notepadu ako da.
@SpringBootTest(classes = FirstAppApplication.class)
public class QuestionServiceUnitTest {

    @Autowired
    private QuestionService questionService;

    @MockBean
    private QuestionRepository questionRepository;


    @Test
    public void getAllAnswers() {
        //when
        when(questionRepository.findAllWithoutAnswersCorrect()).thenReturn(Stream
                .of(new Question(12,"truadssdse"),new Question(3,"sadfalse")).collect(Collectors.toList())    );

        for(QuestionResponseDto questionResponseDto:questionService.getAllQuestions()) System.out.println(questionResponseDto.toString());
        // then
        assertEquals(2,questionService.getAllQuestions().size());
        assertEquals(3,questionService.getAllQuestions().get(1).getLevel());
    }

    @Test(expected = EntityNotFoundException.class)
    public void getAnswerByIdTestException()
    {
        when(questionRepository.findById("12L")).thenThrow(new EntityNotFoundException("Question with id 12 doesn't exist"));

        questionService.getQuestionById("12L");
    }

    @Test
    public void getAnswerByIdTestCaughtException()
    {
        when(questionRepository.findById("12L")).thenThrow(new EntityNotFoundException("Question with id 12 doesn't exist"));

        try
        {
            questionService.getQuestionById("12L");
        }
        catch(Exception ex)
        {
            assertEquals(ex.getClass(), EntityNotFoundException.class);
        }

    }

    @Test
    public void getAnswerByIdTest()
    {
        when(questionRepository.findByIdWithoutAnswersCorrect("13L")).thenReturn(Optional.of(new Question(14, "pitanje3")));

        assertEquals(  questionService.getQuestionById("13L").getQuestionText(),"pitanje3"  );
        //assertThat(answerService.getAnswerById(13L).getText(),is("Answer of the question"));  -> DEPRECATED
        // verify(userRepository).findOne(1l) == verify(userRepository,times(1)).findOne(1l) -> rep je pozvao metodu findOne(1l ###? jel bas broji sa id 1 ili ce pikat i ostale ?) ### 1 put.
    }

    @Before
    public void before() {
        System.out.println("Before");
    }

    @After
    public void after() {
        System.out.println("After");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After Class");
    }


}
