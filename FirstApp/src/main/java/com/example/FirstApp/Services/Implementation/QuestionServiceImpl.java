package com.example.FirstApp.Services.Implementation;

import com.example.FirstApp.Dto.AnswerRequestDto;
import com.example.FirstApp.Dto.Mapper.AnswerDtoMapper;
import com.example.FirstApp.Dto.Mapper.QuestionDtoMapper;
import com.example.FirstApp.Dto.QuestionRequestDto;
import com.example.FirstApp.Dto.QuestionResponseDto;
import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Entities.Question;
import com.example.FirstApp.Exceptions.CustomExceptions.EntityNotFoundException;
import com.example.FirstApp.Repositories.QuestionRepository;
import com.example.FirstApp.Services.Interface.QuestionService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    @Autowired //
    private QuestionDtoMapper questionDtoMapper;// = Mappers.getMapper(com.example.FirstApp.Dto.Mapper.QuestionDtoMapper.class);

    private AnswerDtoMapper answerDtoMapper = Mappers.getMapper(AnswerDtoMapper.class);

    @Autowired
    public void setQuestionRepository( QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<QuestionResponseDto> getAllQuestions() {

        return questionDtoMapper.questionListToResponseQuestionList((List<Question>) questionRepository.findAll());
    }

    @Override
    public QuestionResponseDto addQuestion(QuestionRequestDto questionRequestDto) {
        // posebni uslov za level < 1 , kakva greska da se desi ?
        if(questionRequestDto.getLevel()!=null && questionRequestDto.getQuestionText()!=null)
        {
            Question question = questionDtoMapper.requestQuestionToQuestion(questionRequestDto);
            questionRepository.save(question);

            return questionDtoMapper.questionToResponseQuestion(question);
        }

        else return null; // odnosno neka greska ili nesto
    }

    @Override
    public void deleteAllQuestion() {
        questionRepository.deleteAll();
    }


    @Override
    public QuestionResponseDto editQuestionById(Long id, QuestionRequestDto questionRequestDto) {

        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if(optionalQuestion.isPresent())
        {
            Question question = questionDtoMapper.requestQuestionToQuestion(questionRequestDto);
            if(question.getLevel()!=null)optionalQuestion.get().setLevel(question.getLevel());
            if(question.getQuestionText()!=null)optionalQuestion.get().setQuestionText(question.getQuestionText());

            // # ovo dvoje moze biti 1 linija #
            questionRepository.save(optionalQuestion.get());
            return questionDtoMapper.questionToResponseQuestion(optionalQuestion.get()); // opcional jer ako je null necu te nullove da mi vraca nego u DB sta je
        }
        else return null; // neka greska da put ne moze mjenjati jer ne postoji taj id
    }

    @Override
    public QuestionResponseDto getQuestionById(Long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if(optionalQuestion.isPresent())
        {
            return questionDtoMapper.questionToResponseQuestion(optionalQuestion.get());
        }
        else throw new EntityNotFoundException("Question with {id="+id+"} doesn't exist");
    }

    @Override
    public void deleteQuestionById(Long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if(optionalQuestion.isPresent())
        {
            questionRepository.deleteById(id);
        }
        else throw new EntityNotFoundException("Answer with {id="+id+"} doesn't exist");
    }

    @Override
    public QuestionResponseDto addAnswer(Long id, AnswerRequestDto answerRequestDto) {

        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if(optionalQuestion.isPresent())
        {
            Question question = optionalQuestion.get();
            Answer answer = answerDtoMapper.requestAnswerToAnswer(answerRequestDto);

            question.setAnswer(answer);
            answer.setQuestion(question); // DA LI JE OVA LINIJA BITNA? % ASK

            return questionDtoMapper.questionToResponseQuestion(questionRepository.save(question));
        }


        else return null; // neka greska da nema pitanja na koje se veze odg
    }

    @Override
    public void deleteQuestionAnswerByIdAnswer(Long id, Long idAnswer) {
        System.out.println("da prvo DTO zavrsim pa cu ovo");
        return ;
    }
}
