package spring.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;

@ConfigurationProperties(prefix = "application")
@Configuration
@Getter
@Setter
public class I18nConfig {

    private String fileName;

    @Bean
    public BufferedReader bufferedReader() {

        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("classpath:/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean
    public Locale locale() {
        return LocaleContextHolder.getLocale();
    }
}

