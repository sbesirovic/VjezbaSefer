package com.example.FirstApp.Models;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AuthenticationRequest {
    @ApiModelProperty(
            value = "username of the user",
            name = "user",
            dataType = "String",
            example = "userProfile")
    private String username;
    @ApiModelProperty(
            value = "password of the user",
            name = "pass",
            dataType = "String",
            example = "userpw")
    private String password;
}
