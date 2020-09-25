package com.example.FirstApp.Dto;

public class AnswerResponseDto {

    private Long id;
    private String text;
    private Boolean correct;
    private Long testiramDtoExplicit;


    public Long getTestiramDtoExplicit() {
        return testiramDtoExplicit;
    }

    public void setTestiramDtoExplicit(Long testiramDtoExplicit) {
        this.testiramDtoExplicit = testiramDtoExplicit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String answerText) {
        this.text = answerText;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

}
