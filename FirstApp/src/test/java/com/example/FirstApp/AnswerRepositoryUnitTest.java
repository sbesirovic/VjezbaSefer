package com.example.FirstApp;



import com.example.FirstApp.Dto.AnswerResponseDto;
import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Exceptions.CustomExceptions.EntityNotFoundException;
import com.example.FirstApp.Repositories.AnswerRepository;
import com.example.FirstApp.Services.Interface.AnswerService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

@RunWith(SpringRunner.class)  // poenta je sto sad moze i bez mocka zvat repo nad DB, pa nez jel bas to uredu da bude kod unit testova ? 100% warning. Jedino da mockujem dtoMapper sa onim nacinom before() u notepadu ako da.
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

        for(AnswerResponseDto answerResponseDto:answerService.getAllAnswers()) System.out.println(answerResponseDto.toString());
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
