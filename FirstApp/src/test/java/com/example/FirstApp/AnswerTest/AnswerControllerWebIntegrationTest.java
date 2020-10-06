package com.example.FirstApp.AnswerTest;

import com.example.FirstApp.FirstAppApplication;


import com.example.FirstApp.JwtUtil;
import com.example.FirstApp.Models.AuthenticationRequest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
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

    private String token = "";

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
        when().request("GET", "/answers/1").then().statusCode(200);
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
        when().request("GET", "/answers/1").then().statusCode(200)
                .body("id",equalTo(1),"correct",equalTo(true));
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

