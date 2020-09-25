package com.example.FirstApp.Dto;

import com.example.FirstApp.Entities.Answer;

import java.util.ArrayList;
import java.util.List;

public class QuestionResponseDto {

    private Long id;

    private Integer level;

    private String questionText;

    private List<AnswerResponseDto> answers;

    public List<AnswerResponseDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerResponseDto> answers) {
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void addAnswer(AnswerResponseDto answerResponseDto) {
        if (answerResponseDto == null) {
            answers = new ArrayList<>();
        }

        answers.add(answerResponseDto);
    }
}
