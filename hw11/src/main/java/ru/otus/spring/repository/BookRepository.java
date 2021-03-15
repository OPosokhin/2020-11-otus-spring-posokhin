package ru.otus.spring.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Book;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String>{

    Flux<Book> findByName(String name);
    Flux<Book> findAllBookInfosBy();

}
