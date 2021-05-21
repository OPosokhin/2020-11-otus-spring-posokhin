package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Genre;

import java.util.List;


public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findByName(String name);
}
