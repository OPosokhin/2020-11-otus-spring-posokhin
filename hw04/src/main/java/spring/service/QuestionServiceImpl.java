package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import spring.dao.QuestionDao;
import spring.domain.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@PropertySource("classpath:application.yml")
@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;
    private final BufferedReader bufferedReader;
    private final MessageSource messageSource;
    private final Locale locale;

    @Autowired
    public QuestionServiceImpl(QuestionDao questionDao, BufferedReader bufferedReader, MessageSource messageSource, Locale locale) {

        this.questionDao = questionDao;
        this.bufferedReader = bufferedReader;
        this.messageSource = messageSource;
        this.locale = locale;
    }

    @Override
    public void startTest(String firstName) throws RuntimeException{

        Student student = questionDao.save(firstName);
        List<String> question = questionDao.getQuestions();
        List<String> questionAnswer = new ArrayList<>();
        StringBuilder answer = new StringBuilder();

        for (String s : question) {
            System.out.println(s + ":");
            try {
                answer.append(bufferedReader.readLine());
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


    private int countRightAnswers(List<String> answers, List<String> questionAnswer) {
        int count = 0;
        for (int i = 0; i < questionAnswer.size(); i++) {
            if (answers.get(i).toLowerCase().trim().equals(questionAnswer.get(i).toLowerCase().trim())) {
                count++;
            }
        }
        return count;
    }

    private void showResultMessage(Student student) {
        System.out.println(messageSource.getMessage("ms.user.name", new String[]{student.getFirstName()}, locale));
        System.out.println(messageSource.getMessage("ms.rightAnswers.count", new String[]{Integer.toString(student.getCountRightAnswers())}, locale));
          if (student.getCountRightAnswers() >= 4) {
              System.out.println(messageSource.getMessage("ms.finish.result.passed", new String[]{":"}, locale));
        } else {
              System.out.println(messageSource.getMessage("ms.finish.result.failed", new String[]{":"}, locale));
        }
    }
}


