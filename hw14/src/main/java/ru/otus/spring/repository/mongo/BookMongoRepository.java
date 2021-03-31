package ru.otus.spring.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.mongo.BookMongo;


public interface BookMongoRepository extends MongoRepository<BookMongo, String> {

}
