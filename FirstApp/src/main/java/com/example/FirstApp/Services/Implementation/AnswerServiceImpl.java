package com.example.FirstApp.Services.Implementation;

import com.example.FirstApp.Entities.Question;
import com.example.FirstApp.Exceptions.CustomExceptions.EntityNotFoundException;
import com.example.FirstApp.Dto.Mapper.AnswerDtoMapper;
import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Repositories.AnswerRepository;
import com.example.FirstApp.Repositories.QuestionRepository;
import com.example.FirstApp.Services.Interface.AnswerService;
import com.vjezba.DTO.AnswerRequestDto;
import com.vjezba.DTO.AnswerResponseDto;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerDtoMapper answerDtoMapper;

    //$###########################
    @Autowired
    private QuestionRepository questionRepository; // Obrisi, u ovoj klasi nema smisla da answer ruta kreira odg
    //###############################

    @Override
    public List<AnswerResponseDto> getAllAnswers() {
        List<AnswerResponseDto> answerResponseDtoList = answerDtoMapper.answerListToAnswerResponseList( (List<Answer>)answerRepository.findAll() );
        return answerResponseDtoList;
    }

    @Override
    public AnswerResponseDto getAnswerById(ObjectId id) {

        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if(optionalAnswer.isPresent())
        {
            AnswerResponseDto answerResponseDto = answerDtoMapper.answerToResponseAnswer(optionalAnswer.get());
            return answerResponseDto; //warning: moze u 1 liniji ali nisam sad htio
        }
        else throw new EntityNotFoundException("Answer with {id="+id+"} doesn't exist");
    }


    //#########################
    @Override
    public AnswerResponseDto addAnswer(AnswerRequestDto answerRequestDto) {

        Answer answer = answerDtoMapper.requestAnswerToAnswer(answerRequestDto);
        answerRepository.save(answer);

        AnswerResponseDto answerResponseDto = answerDtoMapper.answerToResponseAnswer(answer);
        return answerResponseDto;

    }

    @Override
    public Answer connectAnswerWithQuestion(ObjectId id, ObjectId idQuestion) {
    Answer answer = answerRepository.findById(id).get();
    answer.setQuestion(questionRepository.findById(idQuestion).get());
    return answerRepository.save(answer);
    }



    @Override
    public void deleteAnswerById(ObjectId id) {
        Optional<Answer> opetionalAnswer = answerRepository.findById(id);
        if(opetionalAnswer.isPresent())
        {
            answerRepository.deleteById(id);
        }
        else throw new EntityNotFoundException("Answer with {id="+id+"} doesn't exist");

        // PROBLEM JE STO AKO OVAKO NAPRAVIM SADA MORAM OBRISATI I IZ RELACIJE TAMO POSEBNO, dok je relacioni mysql sam to brisao
    }

    //###########################3

}
