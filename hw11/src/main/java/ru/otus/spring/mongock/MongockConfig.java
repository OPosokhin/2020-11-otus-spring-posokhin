package ru.otus.spring.mongock;

import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.SpringDataMongo3Driver;
import com.github.cloudyrock.spring.v5.MongockSpring5;
import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import com.mongodb.MongoClient;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.mongock.changelog.DatabaseChangelog;

@Configuration
public class MongockConfig {
    @Bean
    public MongockSpring5.MongockInitializingBeanRunner mongockApplicationRunner(
            ApplicationContext springContext,
            MongoTemplate mongoTemplate
    ) {
        return MongockSpring5.builder()
                .setDriver(SpringDataMongo3Driver.withDefaultLock(mongoTemplate))
                .addChangeLogsScanPackage("ru.otus.spring.mongock.changelog")
                .setSpringContext(springContext)
                .buildInitializingBeanRunner();
    }
}
