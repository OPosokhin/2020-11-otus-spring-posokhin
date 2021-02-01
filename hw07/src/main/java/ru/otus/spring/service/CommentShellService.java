package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;

@ShellComponent
@RequiredArgsConstructor
public class CommentShellService {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @ShellMethod(value = "Find comment by id ", key = {"commentById"})
    public String getById(@ShellOption long id) {
        return commentRepository.findById(id).toString();
    }

    @Transactional(readOnly = true)
    @ShellMethod(value = "Find list comment by book id", key = {"commentsListByBookId"})
    public String getByBookId(@ShellOption long id) {
        Book book = bookRepository.findById(id).get();
        return book.getComments().toString();
    }

    @ShellMethod(value = "Add comment by book id and text", key = {"addComment"})
    public String addComment(@ShellOption long id, @ShellOption String text) {
        return commentRepository.save(new Comment(text, new Book(id))).toString();
    }

    @ShellMethod(value = "Delete comment by id", key = {"deleteComment"})
    public void delete(@ShellOption long id) {
        commentRepository.deleteById(id);
    }
}
