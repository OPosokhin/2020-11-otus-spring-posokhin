package ru.otus.spring.repository;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.spring.domain.Author;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Author JPA repository test")
@DataMongoTest
@EnableMongock
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository repositoryJpa;
    @Autowired
    private MongoTemplate mongoTemplate;

    @DisplayName("should find all")
    @Test
    void shouldfindAll() {
        assertThat(repositoryJpa.findAll().size()).isEqualTo(4);
    }

    @DisplayName("should find entity by name")
    @Test
    void findByName() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("J.K.Rowling"));
        Author expectedAuthor = mongoTemplate.find(query, Author.class).get(0);
        Author actualAuthor = repositoryJpa.findByName(expectedAuthor.getName()).get(0);
        assertEquals(expectedAuthor, actualAuthor);
    }

    @DisplayName("should author id on save")
    @Test
    void saveIdTest() {
        Author newAuthor = new Author();
        newAuthor.setName("new author");
        repositoryJpa.save(newAuthor);
        assertNotNull(newAuthor.getId());
    }
}