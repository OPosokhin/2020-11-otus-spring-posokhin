package ru.otus.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Class Student")
public class StudentTest {
    private static final String FIRST_NAME = "Ivan";
    private static final String LAST_NAME = "Pupkin";

    @DisplayName("Firstname is correct")
    @Test
    void getFirstName() {
        Student student = new Student(FIRST_NAME,LAST_NAME);
        student.setFirstName(FIRST_NAME);
        assertEquals(FIRST_NAME, student.getFirstName());
    }

    @DisplayName("Lastname is correct")
    @Test
    void getLastName() {
        Student student = new Student(FIRST_NAME,LAST_NAME);
        student.setLastName(LAST_NAME);
        assertEquals(LAST_NAME, student.getLastName());
    }
}
