package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.domain.Author;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShellService {
    private final AuthorDao authorDao;

    @ShellMethod(value = "Find author by id", key = {"authorById"})
    public String getById(@ShellOption Long id) {

        return authorDao.getById(id).toString();
    }

    @ShellMethod(value = "Find author by name", key = {"authorByName"})
    public String getById(@ShellOption String name) {

        return authorDao.getByName(name).toString();
    }

    @ShellMethod(value = "Add new author (id, name)", key = {"addAuthor"})
    public String addAuthor(@ShellOption long id, @ShellOption String name) {
        Author author = new Author(id, name);
        authorDao.insert(author);
        return author.toString();
    }

    @ShellMethod(value = "Get all authors", key = {"authors"})
    public String getAll() {
        return authorDao.getAll().toString();
    }

    @ShellMethod(value = "Delete author by id", key = {"deleteAuthor"})
    public String delete(@ShellOption long id) {
        return Boolean.toString(authorDao.delete(id));
    }
}
