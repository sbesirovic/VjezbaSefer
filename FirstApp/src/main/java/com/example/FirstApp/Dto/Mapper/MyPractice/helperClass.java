package com.example.FirstApp.Dto.Mapper.MyPractice;

/*
import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Entities.Question;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Named("helperClass")
public class helperClass {

    @Named("questionToAnswers")
    public List<Answer> questionToListAnswers(Question question) {
        if (question.getAnswers() != null || !question.getAnswers().isEmpty())
            return question.getAnswers();
        else
            return null;
    }
}*/

import com.example.FirstApp.Dto.Mapper.MyPractice.Firstelement;
import com.example.FirstApp.Entities.Question;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class helperClass {

   /*
   private Question question;

   @Firstelement
    public TestClass allListblabla (Question question ) {
        if (question.getAnswers()!=null) {
            return new TestClass(question.getAnswers());¸¸¸¸c  NE RADI
        } else {
            return null;
        }
    }*/

    /*   NON ITERABLE TO ITERALE
    @Firstelement
    public <T> List<T> toList(T in) {
        if (in != null) return Collections.singletonList(in);
        else return new ArrayList<T>();
    }*/

    /*ITERABLE TO NON ITERABLE
    @Firstelement
    public <T> T first( List<T> in ) {
        if ( in != null && !in.isEmpty() ) {
           // if(in.get(0).getClass() == Question.class)  question = (Question) in.get(0);
            return in.get( 0 );
        }
        else {
            return null;
        }
    }*/

}