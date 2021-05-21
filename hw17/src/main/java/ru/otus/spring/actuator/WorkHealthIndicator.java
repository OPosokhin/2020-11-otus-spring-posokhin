package ru.otus.spring.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class WorkHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        LocalTime now = LocalTime.now();
        boolean serverIsDown = now.isBefore(LocalTime.of(10, 0, 0))
                || now.isAfter(LocalTime.of(19, 0, 0));
        if (serverIsDown) {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Отдыхаем!!!")
                    .build();
        } else {
            return Health.up().withDetail("message", "Работаем!!!").build();
        }
    }
}
