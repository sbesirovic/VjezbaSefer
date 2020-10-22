package com.example.FirstApp.Services.Implementation;

import com.example.FirstApp.Dto.Mapper.QuestionAnswersDto;
import com.example.FirstApp.Dto.Mapper.AnswerDtoMapper;
import com.example.FirstApp.Dto.Mapper.QuestionDtoMapper;
import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Entities.Question;
import com.example.FirstApp.Exceptions.CustomExceptions.EntityNotFoundException;
import com.example.FirstApp.Repositories.QuestionRepository;
import com.example.FirstApp.Services.Interface.QuestionService;
import com.vjezba.DTO.AnswerRequestDto;
import com.vjezba.DTO.AnswerResponseDto;
import com.vjezba.DTO.QuestionRequestDto;
import com.vjezba.DTO.QuestionResponseDto;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired //
    private QuestionDtoMapper questionDtoMapper;// = Mappers.getMapper(com.example.FirstApp.Dto.Mapper.QuestionDtoMapper.class);


    private AnswerDtoMapper answerDtoMapper = Mappers.getMapper(AnswerDtoMapper.class);




    @Override
    public List<QuestionResponseDto> getAllQuestions() {

        //return questionDtoMapper.questionListToResponseQuestionList((List<Question>) questionRepository.findByLevelAndQuestionText(13,"Question1?"));
        return questionDtoMapper.questionListToResponseQuestionList((List<Question>) questionRepository.findAllWithoutAnswersCorrect());
    }

    @Override
    public QuestionResponseDto getQuestionById(String id) {
        Optional<Question> optionalQuestion = questionRepository.findByIdWithoutAnswersCorrect(id);
        if(optionalQuestion.isPresent())
        {
            return questionDtoMapper.questionToResponseQuestion(optionalQuestion.get());
        }
        else throw new EntityNotFoundException("Question with {id="+id+"} doesn't exist");
    }

    @Override
    public QuestionResponseDto addQuestion( QuestionRequestDto questionRequestDto) {

            Question question = questionDtoMapper.requestQuestionToQuestion(questionRequestDto);
            questionRepository.save(question);

            return questionDtoMapper.questionToResponseQuestion(question);

    }

    @Override
    public QuestionAnswersDto getAllAnswersByQuestionId(String id) {

        Optional<Question>  q = questionRepository.findByIdWithoutAnswersCorrect(id);
        //Optional<Question>  q = questionRepository.findById(id);   ovo vraca i tacnost (correct) odgovora
        if(q.isPresent()) return answerDtoMapper.QuestionToListAnswers(q.get());
        else throw new EntityNotFoundException("Question with {id="+id+"} doesn't exist");
    }

    @Override
    public void deleteQuestionById(String id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if(optionalQuestion.isPresent())
        {
            questionRepository.deleteById(id);
        }
        else throw new EntityNotFoundException("Question with {id="+id+"} doesn't exist");
    }

    @Override
    public void deleteAllQuestion() {
        questionRepository.deleteAll();
    }


    @Override
    public QuestionResponseDto editQuestionById(String id, QuestionRequestDto questionRequestDto) {


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
    public AnswerResponseDto addAnswer(String id, AnswerRequestDto answerRequestDto) {

        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if(optionalQuestion.isPresent())
        {
            Question question = optionalQuestion.get();
            Answer answer = answerDtoMapper.requestAnswerToAnswer(answerRequestDto);
            //BUSSINES LOGIC   SA SAMO JEDNIM TACNIM ODGOVOROM, TAKVI VLAIDATORI SE U PRINCIPU ROIJETKO PRAVE. BITNO DA ZNAS SNOVNE I ONE KAD SE KRIZAJU...
            // neki QuestionValidator i AnswerValidatorHelper da ne bi ovdje sad imao kod, samo pozovem metode validacije il tako nesto.

            question.setAnswer(answer);
            questionRepository.save(question);

            return answerDtoMapper.answerToResponseAnswer(answer);
        }

        else throw new EntityNotFoundException("Question with {id="+id+"} doesn't exist");
    }

    @Override
    public void deleteQuestionAnswerByIdAnswer(String id, String idAnswer) {
        System.out.println("Not implemented");
        return ;
    }


    @Override
    public AnswerResponseDto getAnswerByIdFromQuestionById(String id,String idAnswer) {
       Optional<Question> q = questionRepository.findByIdAnswerById(id,idAnswer);
       if(q.isPresent())
       {
           QuestionAnswersDto questionAnswersDto= answerDtoMapper.QuestionToListAnswers(q.get());
           AnswerResponseDto answerResponseDto = questionAnswersDto.getAnswerById(idAnswer);
           if(answerResponseDto!=null)
           {
               return answerResponseDto;
           }
           else throw new EntityNotFoundException("Answer with {id="+idAnswer+"} doesn't exist");
       }
       else throw new EntityNotFoundException("Question with {id="+id+"} doesn't exist");// ili nema odg taj
       //return answerDtoMapper.QuestionToListAnswers(q.get()).getAnswers().get(0);
          //answerDtoMapper.answerToResponseAnswer(q.get());
    }
}
