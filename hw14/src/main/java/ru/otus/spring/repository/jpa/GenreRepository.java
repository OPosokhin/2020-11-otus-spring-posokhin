package ru.otus.spring.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.jpa.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
