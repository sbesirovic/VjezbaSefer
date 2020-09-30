package com.example.FirstApp.Validators;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OnlyOneTrueValidator.class)
public @interface OnlyOneTrue {
    String message() default "PORUKA IZ MOJE ANOTACIJE TESTIRAM";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
