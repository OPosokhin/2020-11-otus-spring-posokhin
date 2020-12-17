package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Student {

    private final String firstName;
    private final String lastName;
    private int countAnswers;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getCountRightAnswers() {
        return countAnswers;
    }

    public void setCountRightAnswers(int countAnswers) {
        this.countAnswers = countAnswers;
    }
}
