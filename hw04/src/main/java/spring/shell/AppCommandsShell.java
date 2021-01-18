package spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.MessageSource;
import org.springframework.context.event.EventListener;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import spring.service.QuestionService;

import java.util.Locale;

@ShellComponent
@RequiredArgsConstructor
public class AppCommandsShell {

    private final QuestionService questionService;
    private String firstName;
    private final MessageSource messageSource;
    private final Locale locale;

    @EventListener
    public void printInvite(ApplicationStartedEvent event) {
        System.out.println(messageSource.getMessage("ms.user",new String[]{": "} , locale));
        System.out.print(messageSource.getMessage("ms.user.firstName",new String[]{": "} , locale));
    }
    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "AnyUser") String firstName) throws Exception {
        this.firstName = firstName;
        return messageSource.getMessage("message.start", new String[]{this.firstName}, locale);
    }

    @ShellMethod(value = "Start testing student application", key = {"s", "start"})
    public void start() throws Exception {
        questionService.startTest(firstName);
    }

}
