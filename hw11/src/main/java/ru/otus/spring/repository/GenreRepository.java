package ru.otus.spring.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;

import java.util.Optional;

@Repository
public interface GenreRepository extends ReactiveMongoRepository<Genre, String>{

    Optional<Genre> findByName(String name);
}
