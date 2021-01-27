package ru.otus.spring.repository;

import ru.otus.spring.domain.Genre;
import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Optional<Genre> findById(long id);

    long count();

    Genre save(Genre genre);

    List<Genre> findByName(String name);

    List<Genre> findAll();

    void delete(long id);
}
