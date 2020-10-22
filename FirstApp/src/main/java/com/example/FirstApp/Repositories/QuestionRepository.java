package com.example.FirstApp.Repositories;

import com.example.FirstApp.Entities.Answer;
import com.example.FirstApp.Entities.Question;
import com.vjezba.DTO.AnswerResponseDto;
import com.vjezba.DTO.QuestionResponseDto;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
// conditional missing bean  ugl oko ovog sto sam pitao sa bazama
@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {

    // Ono programiranje imenima zaboravio naziv tog tipa progr.
   List<Question> findByLevelAndQuestionText(int level,String questionText);

    //   VJEZBA DA VRATI SAMO ODREDENA POLJA ENTITETA ALI ne moze dodjeliti List<Answers> jer je po tipu on citav samo ne vraca sve podatke vec ih popuni sa "null"
    @Query(value="{'id': ?0}" /*,fields = "{'answers':{'correct':0} }"*/) // ?0 znaci da je ovo prvi parametar metode ispod.
    Optional<Question> findByIdWithoutAnswersCorrect (String id);

    @Query(value="{}"/*,fields = "{'answers':{'correct':0} }"*/) // jer za fields mora imat i i value a ovo je query koji dohvaca sve
    List<Question> findAllWithoutAnswersCorrect ();


    /*Practice
    @Query(value="{ $and : [{'answers.answerText':'jel to nece'},{'answers.id':?0}] }" ,fields = "{'answers':{'correct':0} }") // ?0 znaci da je ovo prvi parametar metode ispod.
    Optional<Question> findByIdAnswerById (String id);
    */


     @Query(value="{$and:[{'id': ?0},{'answers.id':?1}]}") // ?0 znaci da je ovo prvi parametar metode ispod.
     Optional<Question> findByIdAnswerById (String id,String idAnswer);

}

// fields JE LOSE KORISTITI DA SAKRIJES vrijednosti od neautorizovanih klijenata jer ga stavlja na null i niko ga ne vidi...
// zato se i koriste DTO tako da ono...
//****? A KAKO BI U OVISNOSTI OD AUTORITETA BIRALI DA LI CE SE ? DA NIJE DVA DTO I DVA RAZLICITA APIJA VEC ISTI API 2 PONASANJA ??


