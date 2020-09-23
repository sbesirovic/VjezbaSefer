package com.example.FirstApp.Repositories;

import com.example.FirstApp.Entities.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question,Long> {

}
