package spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.domain.Student;
import spring.service.IOService;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao {

    private final IOService ioService;

    @Autowired
    public QuestionDaoImpl(IOService ioService) {

        this.ioService = ioService;
    }

    @Override
    public List<String> getQuestions() {
        String content = ioService.readFile();
        List<String> questions = new ArrayList<>();
        String[] str = content.split("\n");

        for (int i = 1; i < str.length; i++) {
            questions.add(str[i].replaceAll("(,.+)", ""));
        }
        return questions;
    }

    @Override
    public List<String> getAnswers() {
        String content = ioService.readFile();
        String[] str = content.split("\n");

        List<String> answers = new ArrayList<>();
        for (int i = 1; i < str.length; i++) {
            System.out.println(str[i]);
            answers.add(str[i].replaceAll("(.+,)", ""));
        }
        return answers;

    }

    public Student save(String firstName) {

        return new Student(firstName);
    }

}


