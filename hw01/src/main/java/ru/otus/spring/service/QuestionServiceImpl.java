package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;

public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao dao;



    public QuestionServiceImpl(QuestionDao dao) {

        this.dao = dao;
    }

    @Override
    public String getQuestions() {

        return this.dao.getQuestions();
    }
}
