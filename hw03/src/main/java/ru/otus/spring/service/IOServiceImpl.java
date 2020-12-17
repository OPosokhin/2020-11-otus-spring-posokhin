package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.config.I18nConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

@Service
public class IOServiceImpl implements IOService {

    private final Locale locale;
    private final String fileName;

    public IOServiceImpl(I18nConfig i18nConfig, Locale locale) {
        this.fileName = i18nConfig.getFileName();
        this.locale = locale;
    }

    @Override
    public String readFile() {
        String data = null;
        try (InputStream stream = ClassLoader.getSystemResourceAsStream("i18n/" + fileName + '_' + locale + ".csv")) {
            data = new String(stream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}

