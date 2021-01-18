package spring.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Student {

    private final String firstName;
    private int countAnswers;

    public Student(String firstName) {
        this.firstName = firstName;

    }

    public int getCountRightAnswers() {
        return countAnswers;
    }

    public void setCountRightAnswers(int countAnswers) {
        this.countAnswers = countAnswers;
    }
}
