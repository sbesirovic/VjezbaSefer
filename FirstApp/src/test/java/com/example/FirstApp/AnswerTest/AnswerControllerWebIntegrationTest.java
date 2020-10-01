package com.example.FirstApp.AnswerTest;

import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.FirstAppApplication;

import com.example.FirstApp.Repositories.AnswerRepository;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = FirstAppApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class AnswerControllerWebIntegrationTest {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }



    @Test
    public void whenRequestedGetAnswers_thenOK()
    {
        when().request("GET", "/answers").then().statusCode(200);
    }

    @Test
    public void whenRequestedGetAnswer_thenOK()
    {
        when().request("GET", "/answers/1").then().statusCode(200);
    }

    @Test
    public void whenRequestedGetAnswer_thenNOT_FOUND()
    {
        when().request("GET", "/answers/656").then().statusCode(404);
    }

    @Test
    public void answer_resource_returns_200_with_expected_id_and_correct()
    {
        when().request("GET", "/answers/1").then().statusCode(200)
                .body("id",equalTo(1),"correct",equalTo(true));
    }





}


//RestAssured bibl za ovo se koriit umjesto resttemplate on kaze !!!!!!!!!! 100% WARNING omorit sad