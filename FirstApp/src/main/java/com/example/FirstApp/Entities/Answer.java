package com.example.FirstApp.Entities;

import com.example.FirstApp.Validators.OnlyOneTrue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.persistence.*;
import javax.sound.midi.MidiMessage;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Random;

@Document
@Entity
@Setter @Getter @NoArgsConstructor @ToString
public class Answer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PROTECTED)
    private String id;

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
