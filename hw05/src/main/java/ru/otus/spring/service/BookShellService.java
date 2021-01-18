package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class BookShellService {
    private final BookDao bookDao;

    @ShellMethod(value = "Find book by id", key = {"bookById"})
    public String getById(@ShellOption Long id) {
        return bookDao.getById(id).toString();
    }

    @ShellMethod(value = "Find author by name", key = {"bookByName"})
    public String getById(@ShellOption String name) {

        return bookDao.getByName(name).toString();
    }

    @ShellMethod(value = "Save new book (id, name, authors, genres))", key = {"saveBook"})
    public String addBook(@ShellOption long id, @ShellOption String name, @ShellOption(defaultValue = "") String authorIds, @ShellOption(defaultValue = "") String genreIds) {
        Set<Author> authors;
        if (!authorIds.isEmpty()) {
            authors = Arrays.asList(authorIds.split(",")).stream().mapToLong(Long::parseLong)
                    .mapToObj(i -> new Author(i, "")).collect(Collectors.toSet());
        } else {
            authors = new HashSet<>();
        }
        Set<Genre> genres;
        if (!genreIds.isEmpty()) {
            genres = Arrays.asList(authorIds.split(",")).stream().mapToLong(Long::parseLong)
                    .mapToObj(i -> new Genre(i, "")).collect(Collectors.toSet());
        } else {
            genres = new HashSet<>();
        }
        Book book = new Book(id, name, authors, genres);
        bookDao.insert(book);
        return bookDao.getById(id).toString();
    }

    @ShellMethod(value = "Get all books", key = {"allbooks"})
    public String getAll() {

        return bookDao.getAll().toString();
    }

    @ShellMethod(value = "Delete book by id", key = {"deleteBook"})
    public String delete(@ShellOption long id) {

        return Boolean.toString(bookDao.delete(id));
    }
}
