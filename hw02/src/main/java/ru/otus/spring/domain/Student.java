package ru.otus.spring.domain;

public class Student {

    private String firstName;
    private String lastName;
    private int countAnswers;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCountRightAnswers() {
        return countAnswers;
    }

    public void setCountRightAnswers(int countAnswers) {
        this.countAnswers = countAnswers;
    }
}
