package com.example.FirstApp.Services.Implementation;

import com.example.FirstApp.Dto.Mapper.AnswerDtoMapper;
import com.example.FirstApp.Dto.Mapper.QuestionDtoMapper;
import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Entities.Question;
import com.example.FirstApp.Exceptions.CustomExceptions.EntityNotFoundException;
import com.example.FirstApp.Repositories.AnswerRepository;
import com.example.FirstApp.Repositories.QuestionRepository;
import com.example.FirstApp.Services.Interface.QuestionService;
import com.vjezba.DTO.AnswerRequestDto;
import com.vjezba.DTO.AnswerResponseDto;
import com.vjezba.DTO.QuestionRequestDto;
import com.vjezba.DTO.QuestionResponseDto;
import org.bson.types.ObjectId;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private AnswerRepository answerRepository;

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
    public QuestionResponseDto addQuestion( QuestionRequestDto questionRequestDto) {

            Question question = questionDtoMapper.requestQuestionToQuestion(questionRequestDto);
            questionRepository.save(question);

            return questionDtoMapper.questionToResponseQuestion(question);

    }

    @Override
    public void deleteAllQuestion() {
        questionRepository.deleteAll();
    }


    @Override
    public QuestionResponseDto editQuestionById(ObjectId id, QuestionRequestDto questionRequestDto) {


        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if(optionalQuestion.isPresent())
        {
            //
            String patternStr = ".*\\S.*"; // patern da nije string od samih spaceova (regexi za kompleksnije stvari)
            Pattern pattern = Pattern.compile(patternStr);
            //Matcher matcher = pattern.matcher(questionRequestDto.getQuestionText());   da ne bi pravio if null dole sam odma u hodu
            //

            Question question = questionDtoMapper.requestQuestionToQuestion(questionRequestDto);
            if(question.getLevel()!=null) optionalQuestion.get().setLevel(question.getLevel());
            if(question.getQuestionText()!=null && pattern.matcher(question.getQuestionText()).matches() ) optionalQuestion.get().setQuestionText(question.getQuestionText());

            // # ovo dvoje moze biti 1 linija #
            questionRepository.save(optionalQuestion.get());
            return questionDtoMapper.questionToResponseQuestion(optionalQuestion.get()); // opcional jer ako je null necu te nullove da mi vraca nego u DB sta je
        }
        else throw new EntityNotFoundException("Question with {id="+id+"} doesn't exist");
    }

    @Override
    public QuestionResponseDto getQuestionById(ObjectId id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if(optionalQuestion.isPresent())
        {
            return questionDtoMapper.questionToResponseQuestion(optionalQuestion.get());
        }
        else throw new EntityNotFoundException("Question with {id="+id+"} doesn't exist");
    }

    @Override
    public void deleteQuestionById(ObjectId id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if(optionalQuestion.isPresent())
        {
            questionRepository.deleteById(id);
        }
        else throw new EntityNotFoundException("Question with {id="+id+"} doesn't exist");
    }

    @Override
    public AnswerResponseDto addAnswer(ObjectId id, AnswerRequestDto answerRequestDto) {

        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if(optionalQuestion.isPresent())
        {
            Question question = optionalQuestion.get();
            Answer answer = answerDtoMapper.requestAnswerToAnswer(answerRequestDto);
//BUSSINES LOGIC   SA SAMO JEDNIM TACNIM ODGOVOROM, TAKVI VLAIDATORI SE U PRINCIPU ROIJETKO PRAVE. BITNO DA ZNAS SNOVNE I ONE KAD SE KRIZAJU...
// neki QuestionValidator i AnswerValidatorHelper da ne bi ovdje sad imao kod, samo pozovem metode validacije il tako nesto.

            //answer.setQuestion(question);
            Answer anw =  answerRepository.save(answer);
            question.setAnswer(anw);
            //anw.setQuestion(null);
            questionRepository.save(question);
            // !? 50% ?   NE SACUVA SAM- pa ja moram oba repozitorija koristiti dok je za mysql jedan save indirektno spasavao oba. Ima li kakav atribut da sam to radi (mada ja svakako volim vise sam)

            return answerDtoMapper.answerToResponseAnswer(answer);
        }

        else throw new EntityNotFoundException("Question with {id="+id+"} doesn't exist");
    }

    @Override
    public void deleteQuestionAnswerByIdAnswer(ObjectId id, ObjectId idAnswer) {
        System.out.println("Not implemented");
        return ;
    }
}
