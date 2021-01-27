package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.service.QuestionServiceImpl;
import ru.otus.spring.service.QuestionService;

@ComponentScan
public class Main {

    public static void main(String[] args) throws Exception{
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QuestionService questionService = context.getBean(QuestionServiceImpl.class);
       questionService.startTest();

    }
}
