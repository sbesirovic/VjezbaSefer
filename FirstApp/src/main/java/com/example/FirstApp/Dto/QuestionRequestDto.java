package com.example.FirstApp.Dto;



import com.example.FirstApp.OnCreate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter @Getter @NoArgsConstructor
public class QuestionRequestDto {

    @Max(value = 15, message ="Level must be less then 16")
    @Min(value = 1, message ="Level must be greater then 0")
    @NotNull
    @NonNull
    private Integer level;

    @NotBlank(message = "Question text must be entered",groups ={OnCreate.class})// Kada se desi prob baca se constraintviolationexcpetion  a ne methodnotvalid
    private String questionText;

}
