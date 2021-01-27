package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repository.AuthorRepository;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShellService {
    private final AuthorRepository authorRepository;

    @ShellMethod(value = "Find author by id", key = {"authorById"})
    public String getById(@ShellOption Long id) {

        return authorRepository.findById(id).toString();
    }

    @ShellMethod(value = "Find author by name", key = {"authorByName"})
    public String getById(@ShellOption String name) {

        return authorRepository.findByName(name).toString();
    }

    @ShellMethod(value = "Add new author (long id, String name)", key = {"addAuthor"})
    public String addAuthor(@ShellOption long id, @ShellOption String name) {
        final Author author = new Author(id, name);
        final Author savedAuthor = authorRepository.save(author);
        return savedAuthor.toString();
    }

    @ShellMethod(value = "Get all authors", key = {"authors"})
    public String getAll() {
        return authorRepository.findAll().toString();
    }

    @ShellMethod(value = "Delete author by id (long id)", key = {"deleteAuthor"})
    public void delete(@ShellOption long id) {
        authorRepository.deleteById(id);
    }
}

