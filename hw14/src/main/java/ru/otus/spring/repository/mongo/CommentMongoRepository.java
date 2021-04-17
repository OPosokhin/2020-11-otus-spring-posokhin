package ru.otus.spring.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.mongo.CommentMongo;


public interface CommentMongoRepository extends MongoRepository<CommentMongo, String> {

}
