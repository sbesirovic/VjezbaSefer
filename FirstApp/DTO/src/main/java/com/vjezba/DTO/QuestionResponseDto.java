package com.vjezba.DTO;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;


import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class QuestionResponseDto {

    private String id;

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
