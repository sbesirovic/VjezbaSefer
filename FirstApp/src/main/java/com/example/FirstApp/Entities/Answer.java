package com.example.FirstApp.Entities;


import lombok.*;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



@Setter @Getter @NoArgsConstructor @ToString
public class Answer{

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    @Setter(AccessLevel.PROTECTED)
    @MongoId
    private String id = new ObjectId().toString();

    @Version
    private Integer version;

    @NotBlank(message = "Answer text must be entered")
    private String answerText;

    @NonNull
    @NotNull
    private Boolean correct;


    //@ManyToOne
    //@JoinColumn(name="question_id")
    /*@JsonIgnore   NIKJE POMOGLO SA MONGO rekurzijom
    @Getter(AccessLevel.PROTECTED)*/
    private Question question;

    public Answer(@NotBlank(message = "Answer text must be entered") String answerText, @NonNull @NotNull Boolean correct) {
        this.answerText = answerText;
        this.correct = correct;
    }

    public Long getPractice()
    {
        return 1112L;
    }
}
