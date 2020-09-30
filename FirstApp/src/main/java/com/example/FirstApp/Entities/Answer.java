package com.example.FirstApp.Entities;

import com.example.FirstApp.Validators.OnlyOneTrue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import javax.persistence.*;
import javax.sound.midi.MidiMessage;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Setter @Getter @NoArgsConstructor
public class Answer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    @Version
    private Integer version;

    @NotBlank(message = "Answer text must be entered")
    private String answerText;

    @NonNull
    @NotNull
    private Boolean correct;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

    public Answer(@NotBlank(message = "Answer text must be entered") String answerText, @NonNull @NotNull Boolean correct) {
        this.answerText = answerText;
        this.correct = correct;
    }

    public Long getId()
    {
        if(id != null) return id;
        else return -1L;
    }
}
