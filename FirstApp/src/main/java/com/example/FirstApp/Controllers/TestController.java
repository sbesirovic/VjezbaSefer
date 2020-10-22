package com.example.FirstApp.Controllers;


import com.example.FirstApp.Dto.Mapper.QuestionAnswersDto;
import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Entities.Question;
import com.example.FirstApp.OnCreate;
import com.example.FirstApp.OnUpdate;
import com.example.FirstApp.Properties;
import com.example.FirstApp.Services.Interface.QuestionService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.vjezba.DTO.AnswerRequestDto;
import com.vjezba.DTO.AnswerResponseDto;
import com.vjezba.DTO.QuestionRequestDto;
import com.vjezba.DTO.QuestionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController                             //@Controler nije radilo pa ovo staviom, vidi razlike
@RequestMapping (value = "/tests")
@Validated

@Profile({"dev","test"})
public class TestController {

   /* @Value("some message")
    private String information;

    @Value("${my.osoba.godine}")
    private String detailInformation;
    */

    @Autowired // drugacije nije htio da mi pokupi @Value iz Properties - istraziti %
    private Properties properties;

    @GetMapping (path = "/test")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String testProfiles()
    {
        return "DATABASE NAME: "+ properties.getDatabaseName()+"\nActive Profiles:  "+ properties.getActiveProfiles();
    }


}
