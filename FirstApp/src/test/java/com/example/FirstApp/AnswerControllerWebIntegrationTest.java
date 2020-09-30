package com.example.FirstApp;

import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Repositories.AnswerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
//@SpringBootTest(classes = FirstAppApplication.class)
//@SpringBootTest(classes = FirstAppApplication.class, properties = ("server.port=8080"))
@SpringBootTest(classes = FirstAppApplication.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)


//@WebAppConfiguration // radi i bez ove stvari, tako da treba provjerit za sta je ovo tacno
//@WebIntegrationTest
public class AnswerControllerWebIntegrationTest {


    @Test
    public void testGetAnswerById() throws IOException
    {
        RestTemplate restTemplate = new RestTemplate(); // ? new TestRestTemplate
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/answers/218",String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

}
