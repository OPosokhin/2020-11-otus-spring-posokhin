package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DisplayName("Service StudentDao")
public class QuestionDaoImplTest {
    @Autowired
    QuestionDao questionDao;

    @Test
    @DisplayName("Size questions csv file")
    void checkQuestionsCount() {
        assertEquals(questionDao.getQuestions().size(), 5);
    }

    @Test
    @DisplayName("Size aswers csv file")
    void checkAnswersCount() {
        assertEquals(questionDao.getAnswers().size(), 5);
    }

    @Test
    @DisplayName("Returns a non-empty string")
    public void checkReadFile() {
        assertNotNull(questionDao.getQuestions());
    }

}
