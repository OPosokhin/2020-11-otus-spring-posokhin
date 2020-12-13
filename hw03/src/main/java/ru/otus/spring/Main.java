package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.config.I18nConfig;
import ru.otus.spring.service.QuestionService;
import ru.otus.spring.service.QuestionServiceImpl;

@SpringBootApplication
@EnableConfigurationProperties(I18nConfig.class)
public class Main {

    public static void main(String[] args) throws RuntimeException {

        ApplicationContext context = SpringApplication.run(Main.class, args);

        QuestionService questionService = context.getBean(QuestionServiceImpl.class);
        questionService.startTest();
    }

}
