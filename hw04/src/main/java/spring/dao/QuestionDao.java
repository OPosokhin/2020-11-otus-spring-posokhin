package spring.dao;

import spring.domain.Student;

import java.util.List;

public interface QuestionDao {
    List<String> getQuestions();

    List<String> getAnswers();

    Student save(String firstName);

}
