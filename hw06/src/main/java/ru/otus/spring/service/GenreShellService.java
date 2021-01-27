package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepository;


@ShellComponent
@RequiredArgsConstructor
public class GenreShellService {
    private final GenreRepository repository;

    @ShellMethod(value = "Find genre by id (long id)", key = {"genreById"})
    public String getById(@ShellOption Long id) {
        return repository.findById(id).toString();
    }

    @ShellMethod(value = "Find genre by name (String name)", key = {"genreByName"})
    public String getById(@ShellOption String name) {
        return repository.findByName(name).toString();
    }

    @ShellMethod(value = "Add new genre (long id, String name)", key = {"addGenre"})
    public String addGenre(@ShellOption String name) {
        final Genre genre = new Genre(name);
        final Genre savedGenre = repository.save(genre);
        return savedGenre.toString();
    }

    @ShellMethod(value = "Get all genres", key = {"genres"})
    public String getAll() {
        return repository.findAll().toString();
    }

    @ShellMethod(value = "Delete genre by id (long id)", key = {"deleteGenre"})
    public void delete(@ShellOption long id) {
        repository.delete(id);
    }
}
