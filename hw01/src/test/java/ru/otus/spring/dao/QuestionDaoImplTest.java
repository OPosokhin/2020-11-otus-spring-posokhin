package ru.otus.spring.dao;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class QuestionDaoImplTest {

    static QuestionDao questionDao;

    @BeforeAll
    public static void init() {
        questionDao = new QuestionDaoImpl("questions.csv");

    }

    @Test
    public void checkReadFile() {
        assertNotNull(questionDao.getQuestions());
    }

    @Test
    public void checkQuestions() {
        System.out.println(questionDao.getQuestions());
        assertThat(questionDao.getQuestions(), CoreMatchers.containsString("The capital of Bulgaria is"));
        assertThat(questionDao.getQuestions(), CoreMatchers.containsString("The highest mountain in the world is"));
        assertThat(questionDao.getQuestions(), CoreMatchers.containsString("Best programming language is"));
        assertThat(questionDao.getQuestions(), CoreMatchers.containsString("The longest river in the world is"));
        assertThat(questionDao.getQuestions(), CoreMatchers.containsString("Surname of the first president of the USA is"));

    }
}
