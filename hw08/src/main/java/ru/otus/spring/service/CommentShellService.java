package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;

import java.util.UUID;

@ShellComponent
@RequiredArgsConstructor
public class CommentShellService {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @ShellMethod(value = "Find comment by id ", key = {"commentById"})
    public String getById(@ShellOption String id) {
        return commentRepository.findById(id).toString();
    }

    @ShellMethod(value = "Find comment by book id (String bookId, String commentId)", key = {"commentsByBookId"})
    public String getByBookId(@ShellOption String bookId, @ShellOption UUID commentId) {
        return bookRepository.findById(bookId).get().getComments().get(commentId).toString();
    }

    @ShellMethod(value = "Add comment by book id", key = {"addComment"})
    public String addComment(@ShellOption String bookId, @ShellOption String text) {
        Book book = bookRepository.findById(bookId).get();
        Comment comment = new Comment(text);
        UUID id = UUID.randomUUID();
        book.getComments().put(id, comment);
        bookRepository.save(book);
        return id + " " + comment.toString();
    }

    @ShellMethod(value = "Delete comment from book by id (String bookId, UUID commentId)", key = {"deleteComment"})
    public void delete(@ShellOption String bookId, @ShellOption UUID commentId) {
        Book book = bookRepository.findById(bookId).get();
        book.getComments().remove(commentId);
        bookRepository.save(book);
    }
}