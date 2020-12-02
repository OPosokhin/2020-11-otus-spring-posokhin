package ru.otus.spring.dao;

import java.io.IOException;
import java.io.InputStream;

public class QuestionDaoImpl implements QuestionDao {

    private final String filName;


    public QuestionDaoImpl(String filName) {
        this.filName = filName;
    }


    private String readFile() {
        String data = null;

        InputStream stream = ClassLoader.getSystemResourceAsStream(this.filName);
        try {
            data = new String(stream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String getQuestions() {
        String data = this.readFile();
        return data;
    }


}


