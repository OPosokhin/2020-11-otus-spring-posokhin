package ru.otus.spring.repository;

import ru.otus.spring.domain.Book;
import java.util.List;
import java.util.Optional;


public interface BookRepository {

    long count();
    Book save(Book book);

    Optional<Book> findById(long id);

    List<Book> findByName(String name);

    List<Book> findAll();

    void delete(long id);
}
