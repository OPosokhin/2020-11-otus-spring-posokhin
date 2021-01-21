package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorDao {

    long count();
    void insert(Author author);
    Author getById(long id);
    List<Author> getByName(String name);
    List<Author> getAll();
    boolean delete(long id);
}
