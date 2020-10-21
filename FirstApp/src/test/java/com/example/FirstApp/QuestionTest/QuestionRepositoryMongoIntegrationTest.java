package com.example.FirstApp.QuestionTest;

import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Entities.Question;
import com.example.FirstApp.Repositories.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class QuestionRepositoryMongoIntegrationTest {
    @Autowired
    QuestionRepository questionRepository;


    @Test
    public void testQuestionRepositoryFindAll() {

        // DOK NE PODESIM DA MI SVAKI TEST NE RESETUJE EMBEDED BAZU NE MOGU OVO POMJERITI U @BEFORE
        // when
        Question question =  questionRepository.save(new Question(1,"pitanje 1?"));
        Question question1 = questionRepository.save(new Question(2,"kako si?"));


        //then
        assertThat(questionRepository.findAll()).hasSize(2);
        assertThat(questionRepository.findAll()).extracting("level").contains(2);
    }

    /*
    @Test
    public void testAnswerRepositoryFindById() {
        //when
        Answer answerTrue =  answerRepository.save(new Answer("pitanje",true));
        //then
        assertThat(answerRepository.findById(answerTrue.getId()).get().getCorrect()).isEqualTo(true);
    }
    */

}
