package com.example.FirstApp.Entities;


import com.example.FirstApp.OnCreate;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.persistence.*;
import javax.validation.constraints.*;
//import org.hibernate.validator.constraints.NotEmpty; // promjenio dependency starter spring validation umjesto hibernate validation i radi sada normala notEmpty i notblank
import java.util.ArrayList;

import java.util.List;

@Document
@Entity
@Getter @Setter @NoArgsConstructor
public class Question {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PROTECTED)
    private ObjectId id;

    @Version
    //@JsonProperty nije potrebno vise radi DTO-ova
    private Integer version;


    //@Size(min = 1, max = 15, message = "Level must be between 1 and 15") glup sam ovo radi za duzinu kolekcije...
    @Max(value = 15, message ="Level must be less then 16")
    @Min(value = 1, message ="Level must be greater then 0")
    @NotNull
    @NonNull
    private Integer level;

    //@Column(name="ako zelis posebno ime")
    //@Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")   regex ako ima neki format texta
    @NotBlank(message = "Question text must be entered",groups = {OnCreate.class})// Kada se desi prob baca se constraintviolationexcpetion  a ne methodnotvalid
    private String questionText;


    //@OneToMany(mappedBy = "question", cascade = {CascadeType.ALL,},fetch = FetchType.LAZY/*,targetEntity = Answer.class*/)
    @Size(max = 4, message = "This Question already has 4 answers."/*,groups = {OnCreate.class}*/)
    //@OnlyOneTrue
    @DBRef
    private List<Answer> answers = new ArrayList<Answer>();

    public Question(@Max(value = 15, message = "Level must be less then 16") @Min(value = 1, message = "Level must be greater then 0") @NotNull @NonNull Integer level, @NotBlank(message = "Question text must be entered", groups = {OnCreate.class}) String questionText) {
        this.level = level;
        this.questionText = questionText;
    }

    public void setAnswer(Answer answer) // za fk dodano
    {
        answers.add(answer);
    }
}
