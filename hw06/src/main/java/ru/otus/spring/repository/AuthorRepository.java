package ru.otus.spring.repository;

import ru.otus.spring.domain.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Optional<Author> findById(long id);

    long count();

    Author save(Author genre);

    List<Author> findByName(String name);

    List<Author> findAll();

    void delete(long id);
}
