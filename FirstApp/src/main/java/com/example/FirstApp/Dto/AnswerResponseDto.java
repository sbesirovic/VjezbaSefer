package com.example.FirstApp.Dto;

import lombok.*;

/*@Accessors(fluent = true) umjesto getText() bi pisao samo text() */@Getter @Setter @NoArgsConstructor @ToString
public class AnswerResponseDto {

    private Long id;

    private String text;

    private Boolean correct;

    @Getter(AccessLevel.PROTECTED)
    private Long testiramDtoExplicit;

    public AnswerResponseDto(Long id,String text, Boolean correct) {
        this.id  = id;
        this.text = text;
        this.correct = correct;
    }
}