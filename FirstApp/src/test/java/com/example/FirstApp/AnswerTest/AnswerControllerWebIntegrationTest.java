package com.example.FirstApp.AnswerTest;

import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.FirstAppApplication;

import com.example.FirstApp.Models.AuthenticationRequest;
import com.example.FirstApp.Repositories.AnswerRepository;
import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;




//@DataMongoTest   MISLIM DA OVO ONDA ZABLOKIRA REST ASSURED @ I NE DOBIJAM PORT KAKAV BI TREBAO I NE MOZE KONEKCIJA
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FirstAppApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class AnswerControllerWebIntegrationTest {

    @Autowired
    private AnswerRepository answerRepository;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
        testId = answerRepository.save(new Answer("answ1",true)).getId();
    }

    private String token = "";
    private ObjectId testId;

    @Test
    public void whenAdminRequestedGetAnswers_thenOK()
    {
        setToken(new AuthenticationRequest("adminProfile","adminpw"));

        /*List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        setTokenVol2(new User("adminProfile","adminpw",list));
        */

        given().header("Authorization", "Bearer "+token)
                .get("/answers").then()
                .statusCode(200);

    }

    @Test
    public void whenUserRequestedGetAnswers_thenForbiden()
    {

        setToken(new AuthenticationRequest("userProfile","userpw"));

        given().header("Authorization", "Bearer "+token)
                .get("/answers").then()
                .statusCode(403);

    }

    @Test
    public void whenAdminRequestedGetAnswer_thenOK()
    {

        setToken(new AuthenticationRequest("adminProfile","adminpw"));

        given().header("Authorization", "Bearer "+token).
                when().request("GET", "/answers/"+testId).then().statusCode(200);
    }

    @Test
    public void whenAdminRequestedGetAnswer_thenNOT_FOUND()
    {
        setToken(new AuthenticationRequest("adminProfile","adminpw"));

        given().header("Authorization", "Bearer "+token).
                when().request("GET", "/answers/656").then().statusCode(404);
    }

    @Test
    public void whenAdmin_answer_resource_returns_200_with_expected_id_and_correct()
    {
        setToken(new AuthenticationRequest("adminProfile","adminpw"));

        given().header("Authorization", "Bearer "+token).
                when().request("GET", "/answers/"+testId).then().statusCode(200)
                .body("id",equalTo(testId),"correct",equalTo(true));
    }




    private void setToken(AuthenticationRequest authenticationRequest)
    {
        token =  given()
                .contentType(ContentType.JSON)
                .body(authenticationRequest)
                .when()
                .post("/authenticate")
                .jsonPath()
                .get("jwt");
    }


    /*@Autowired
    private JwtUtil jwtUtil;
    private void setTokenVol2(UserDetails userDetails)
    {
        token = jwtUtil.generateToken(userDetails);
    }
    */

}