package com.example.FirstApp.Validators;

import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OnlyOneTrueValidator implements ConstraintValidator<OnlyOneTrue, Answer> {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void initialize(OnlyOneTrue constraintAnnotation) {
    }

    @Override
    public boolean isValid(Answer answer, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("NE KORISTI SE U PRAKSI ZA OVO, NESTO SLICNO ZA KRIS KROS I KLAS CUSTOM VALIDACIJE, NIKAKO OVAKVOG TIPA");
        return false;
    }

    // nema jer nije to ta klasa onda mora svaka klasa imat svoj validator sa ovim cudesima???? nij emi dozvolio da implementiram i <anotacija,druga klasa> jer je duplicate 50% error


}
