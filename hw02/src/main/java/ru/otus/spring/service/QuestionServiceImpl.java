package ru.otus.spring.service;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@PropertySource("classpath:application.properties")
@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {

        this.questionDao = questionDao;
    }

    @Override
    public void startTest() throws Exception {

        Student student = infoStudent();

        BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));
        List<String> question = questionDao.getQuestions();
        List<String> questionAnswer = new ArrayList<>();
        StringBuilder answer = new StringBuilder();

        for (String s : question) {
            System.out.println(s + ":");
            try {
                answer.append(reader.readLine());
                if (answer.length() > 0) {
                    questionAnswer.add(answer.toString());
                }
                answer.setLength(0);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        student.setCountRightAnswers(countRightAnswers(questionDao.getAnswers(), questionAnswer));
        showResultMessage(student);
    }

    private Student infoStudent() {
        BufferedReader readerInfoStudent = new BufferedReader(new InputStreamReader(System.in));
        Student student = null;
        try {
            System.out.print("Please add your first name?: ");
            String firstName = readerInfoStudent.readLine();
            System.out.print("Please add your last name?: ");
            String lastName = readerInfoStudent.readLine();
            student = questionDao.save(firstName, lastName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    private int countRightAnswers(List<String> answers, List<String> questionAnswer) {
        int count = 1;
        for (int i = 1; i < questionAnswer.size(); i++) {
            if (answers.get(i).toLowerCase().trim().equals(questionAnswer.get(i).toLowerCase().trim())) {
                count++;
            }
        }
        return count;
    }

    private void showResultMessage(Student student) {
        System.out.println("Student name: " + student.getLastName() + ", " + student.getFirstName());
        System.out.println("Right answers: " + student.getCountRightAnswers());
        if (student.getCountRightAnswers() >= 5) {
            System.out.println("Congratulations, the exam passed");
        } else {
            System.out.println("Congratulations, the exam has not been passed");
        }
    }
}


