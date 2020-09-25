package com.example.FirstApp.Dto;

public class AnswerRequestDto {

    private String text;
    private Boolean correct;

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
