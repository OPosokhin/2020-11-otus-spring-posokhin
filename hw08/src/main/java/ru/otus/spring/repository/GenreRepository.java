package ru.otus.spring.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.Genre;
import java.util.List;


public interface GenreRepository extends MongoRepository<Genre, String> {

    List<Genre>findByName(String name);
}
