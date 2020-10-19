package com.example.FirstApp.Models;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter @AllArgsConstructor
public class AuthenticationResponse {
    @ApiModelProperty(
            value = "JWT for logged user",
            name = "Json Web Token",
            dataType = "String",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyUHJvZmlsZSIsImV4cCI6MTYwMzA5NzA5NywiaWF0IjoxNjAzMDkzNDk3fQ.jlh0KhQkcOYaAiyuyp8Hre7DkLX2jOpGijsk6PwbpWI")
    private String jwt;
}
