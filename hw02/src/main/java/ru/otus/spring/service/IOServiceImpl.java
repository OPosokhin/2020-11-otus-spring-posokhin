package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class IOServiceImpl implements IOService {
    @Value("${file.name}")

    private String filName;

    @Override
    public String readFile() {
        String data = null;

        try (InputStream stream = ClassLoader.getSystemResourceAsStream(this.filName)) {
            data = new String(stream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}

