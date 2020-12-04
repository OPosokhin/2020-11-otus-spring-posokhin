package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.QuestionService;

public class Main {

    public static void main(String[] args) {
        // TODO: создайте здесь класс контекста
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");

        QuestionService service = context.getBean(QuestionService.class);

        System.out.println(service.getQuestions());

        context.close();
    }
}
