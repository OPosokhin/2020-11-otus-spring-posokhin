package spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Service input/output")
public class IOServiceImplTest {
    @Autowired
    IOService ioService;

    @Test
    @DisplayName("Returns a non-empty string")
    void getNotEmptyString() {
        assertThat(ioService.readFile().length()).isGreaterThan(0);
    }
}

