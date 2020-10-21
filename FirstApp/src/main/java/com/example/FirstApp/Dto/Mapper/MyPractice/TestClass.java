package com.example.FirstApp.Dto.Mapper.MyPractice;

import com.example.FirstApp.Entities.Answer;

import java.util.List;

public class TestClass {

    private List<Answer> answers;
    private Answer answer;

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public TestClass(List<Answer> answers, Answer answer) {
        this.answers = answers;
        this.answer = answer;
    }
}

