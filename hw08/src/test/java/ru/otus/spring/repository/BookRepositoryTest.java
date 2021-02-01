package ru.otus.spring.repository;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.spring.domain.Book;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Book JPA repository test")
@DataMongoTest
@EnableMongock
class BookRepositoryTest {
    public static final long FIRST_BOOK_ID = 1;
    public static final int EXPECTED_QUERIES_COUNT_PER_BOOK = 1;
    @Autowired
    private BookRepository repositoryJpa;

    @Autowired
    private MongoTemplate mongoTemplate;

    @DisplayName("should find entity by name")
    @Test
    void findByName() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("Harry Potter and the Philosophers Stone"));
        Book expectedAuthor = mongoTemplate.find(query, Book.class).get(0);
        Book actualAuthor = repositoryJpa.findByName(expectedAuthor.getName()).get(0);
        assertEquals(expectedAuthor, actualAuthor);
    }

    @DisplayName("should generate id on save")
    @Test
    void saveIdTest() {
        Book newBook = new Book();
        newBook.setName("new book");
        repositoryJpa.save(newBook);
        assertNotNull(newBook.getId());
    }

}