package com.example.FirstApp.Dto;

import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.OnCreate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class QuestionResponseDto {

    private Long id;

    private Integer level;

    private String questionText;

    //@Size(max = 4, message = "This Question already has 4 answers.",groups = {OnCreate.class})
    private List<AnswerResponseDto> answers;



    public void addAnswer(AnswerResponseDto answerResponseDto) {
        if (answerResponseDto == null) {
            answers = new ArrayList<>();
        }

        answers.add(answerResponseDto);
    }
}
