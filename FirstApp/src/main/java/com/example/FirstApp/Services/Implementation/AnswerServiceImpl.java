package com.example.FirstApp.Services.Implementation;

import com.example.FirstApp.Exceptions.CustomExceptions.EntityNotFoundException;
import com.example.FirstApp.Dto.AnswerRequestDto;
import com.example.FirstApp.Dto.AnswerResponseDto;
import com.example.FirstApp.Dto.Mapper.AnswerDtoMapper;
import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Repositories.AnswerRepository;
import com.example.FirstApp.Repositories.QuestionRepository;
import com.example.FirstApp.Services.Interface.AnswerService;
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
        List<AnswerResponseDto> answerResponseDtoList = answerDtoMapper.answerResponseListToAnswerList((List<Answer>)answerRepository.findAll());
        return answerResponseDtoList;
    }

    @Override
    public AnswerResponseDto getAnswerById(Long id) {
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
        // PROVJERIS JEL POSLAO SVE STO TREBA CORECT I TeXT
        Answer answer = answerDtoMapper.requestAnswerToAnswer(answerRequestDto);
        answerRepository.save(answer);

        AnswerResponseDto answerResponseDto = answerDtoMapper.answerToResponseAnswer(answer);
        return answerResponseDto;

    }

    @Override
    public Answer connectAnswerWithQuestion(Long id, Long idQuestion) {
    Answer answer = answerRepository.findById(id).get();
    answer.setQuestion(questionRepository.findById(idQuestion).get());
    return answerRepository.save(answer);
    }
    //###########################3

}
