package com.example.FirstApp.Dto;

import lombok.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;

/*@Accessors(fluent = true) umjesto getText() bi pisao samo text() */ @Getter @Setter @NoArgsConstructor @ToString
public class AnswerResponseDto {

    private Long id;


    private String text;


    private Boolean correct;

    /*
    // @Secured({"ROLE_ADMIN"}) POSTO OVO NE RADI UBACIO SMA RUCNO DOLE
    public Boolean getCorrect() {
        #/ *
        Ovaj dio koda mi je vracao null za usere koji nemaju ovlasti nad ovim poljem dok se povlaci ruta QUESTION
        ### FIELD LEVEL SECURITY RADITI
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean hasAdminRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        if(hasAdminRole) return correct;
        else return null;

        #* /
    }*/

    @Getter(AccessLevel.PROTECTED)
    private Long testiramDtoExplicit;

    public AnswerResponseDto(Long id,String text, Boolean correct) {
        this.id  = id;
        this.text = text;
        this.correct = correct;
    }
}