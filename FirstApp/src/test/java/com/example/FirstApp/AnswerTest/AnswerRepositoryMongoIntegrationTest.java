package com.example.FirstApp.AnswerTest;

import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Repositories.AnswerRepository;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;


import static org.assertj.core.api.Assertions.assertThat;

//@EnableMongoRepositories
@DataMongoTest
//@ExtendWith(SpringExtension.class)
public class AnswerRepositoryMongoIntegrationTest {

    /*@Autowired
    MongoTemplate mongoTemplate;*/

    @Autowired
    AnswerRepository answerRepository;

    @Test
    public void testAnswerRepositoryFindById() {
        //when
        Answer answerTrue =  answerRepository.save(new Answer("pitanje",true));
        //then
        assertThat(answerRepository.findById(answerTrue.getId()).get().getCorrect()).isEqualTo(true);
    }


    @DisplayName("given answers to save"
            + " when save objects using answer repository"
            + " then objects are saved properly")
    @Test
    public void testAnswerRepositoryFindAll() {

        // given
        /*DBObject objectToSave = BasicDBObjectBuilder.start()
                .add("key", "value")
                .get();*/

        /*
        Query query = new Query();
        query.addCriteria(Criteria.where("answerText").is("xd"));
        THIS IS WITH MONGO TEMPLATE
        */

        // when
        Answer answerTrue =  answerRepository.save(new Answer("pitanje",true));
        Answer answerFalse = answerRepository.save(new Answer("xd",false));

        //assertThat(answerRepository.find(query,Answer.class,"Answer") ).extracting("correct").contains(false);
        // then


        assertThat(answerRepository.findAll()).hasSize(2);

        assertThat(answerRepository.findAll()).extracting("correct").contains(false);
    }




}
