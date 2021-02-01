package ru.otus.spring.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {

    List<Author>findByName(String name);

}
