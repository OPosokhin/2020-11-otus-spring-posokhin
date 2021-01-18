package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import spring.config.I18nConfig;


@SpringBootApplication
@EnableConfigurationProperties(I18nConfig.class)
public class Main {

    public static void main(String[] args) throws RuntimeException {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
    }

}
