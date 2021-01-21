package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;


@ShellComponent
@RequiredArgsConstructor
public class GenreShellService {
    private final GenreDao genreDao;

    @ShellMethod(value = "Find genre by id", key = {"genreById"})
    public String getById(@ShellOption Long id) {

        return genreDao.getById(id).toString();
    }

    @ShellMethod(value = "Find genre by name", key = {"genreByName"})
    public String getById(@ShellOption String name) {

        return genreDao.getByName(name).toString();
    }

    @ShellMethod(value = "Add new genre (id, name)", key = {"addGenre"})
    public String addGenre(@ShellOption long id, @ShellOption String name) {
        Genre genre = new Genre(id, name);
        genreDao.insert(genre);
        return genre.toString();
    }

    @ShellMethod(value = "Get all genre", key = {"genre"})
    public String getAll() {
        return genreDao.getAll().toString();
    }

    @ShellMethod(value = "Delete genre by id", key = {"deleteGenre"})
    public String delete(@ShellOption long id) {

        return Boolean.toString(genreDao.delete(id));
    }
}
