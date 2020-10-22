package com.vjezba.DTO;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter @NoArgsConstructor
public class AnswerRequestDto {

    @NotBlank(message = "Answer text must be entered")
    private String text;

    @NotNull
    @NonNull
    private Boolean correct;

    public AnswerRequestDto(@NotBlank(message = "Answer text must be entered") String text, @NotNull @NonNull Boolean correct) {
        this.text = text;
        this.correct = correct;
    }

}
