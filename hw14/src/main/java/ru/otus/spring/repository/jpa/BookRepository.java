package ru.otus.spring.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.otus.spring.domain.jpa.Book;

import java.util.List;

@EnableJpaRepositories
public interface BookRepository extends JpaRepository<Book, Long>  {
    List<Book> findAll();
}
