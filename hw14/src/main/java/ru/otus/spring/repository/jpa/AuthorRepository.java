package ru.otus.spring.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.jpa.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
