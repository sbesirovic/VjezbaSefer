package com.example.FirstApp.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;


@Entity
public class Question {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    //@JsonProperty
    private Integer version;

    private Integer level;

    //@Column(name="ako zelis posebno ime")
    private String questionText;


    @OneToMany(mappedBy = "question", cascade = {CascadeType.ALL,},fetch = FetchType.LAZY/*,targetEntity = Answer.class*/)
    private List<Answer> answers;



    /*public Question()
    {

    }
    public Question(Integer level, String questionText) {
        this.level = level;
        this.questionText = questionText;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty
    public Integer getVersion() {
        return version;
    }

    @JsonIgnore
    public void setVersion(Integer version) {
        this.version = version;
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

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }


    public void setAnswer(Answer answer) // za fk dodano
    {
        answers.add(answer);
    }
}
