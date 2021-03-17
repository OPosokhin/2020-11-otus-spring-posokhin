package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.domain.Author;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Author repository test")
@DataJpaTest
class AuthorRepositoryTest {
    public static final long FIRST_AUTHOR_ID = 1;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private TestEntityManager entityManager;

    @DisplayName("should find entity by id")
    @Test
    void findById() {
        Author expectedAuthor = entityManager.find(Author.class, FIRST_AUTHOR_ID);
        Author actualAuthor = authorRepository.findById(FIRST_AUTHOR_ID).get();
        assertEquals(expectedAuthor, actualAuthor);
    }

    @DisplayName("should count all entities")
    @Test
    void count() {
        long expectedQuantity = entityManager.getEntityManager().createQuery("select a from Author a", Author.class).
                getResultList().size();
        long actualQuantity = authorRepository.count();
        assertEquals(expectedQuantity, actualQuantity);
    }

    @DisplayName("should generate id on save")
    @Test
    void saveIdTest() {
        Author newAuthor = new Author();
        newAuthor.setName("new author");
        authorRepository.save(newAuthor);
        assertNotEquals(0, newAuthor.getId());
    }

    @DisplayName("should persist entity")
    @Test
    void save() {
        Author newAuthor = new Author();
        newAuthor.setName("new author");
        authorRepository.save(newAuthor);
        Author foundAuthor = entityManager.find(Author.class, newAuthor.getId());
        assertEquals(newAuthor, foundAuthor);
    }

    @DisplayName("should find entity by name")
    @Test
    void findByName() {
        Author expectedGenre = entityManager.find(Author.class, FIRST_AUTHOR_ID);
        Author actualAuthor = authorRepository.findByName(expectedGenre.getName()).get(0);
        assertEquals(expectedGenre, actualAuthor);
    }

    @DisplayName("should find all entities")
    @Test
    void findAll() {
        Set<Author> expectedAuthors = new HashSet<>(entityManager.getEntityManager().createQuery("select a from Author a", Author.class).
                getResultList());
        List<Author> actualAuthorsList = authorRepository.findAll();
        Set<Author> actualAuthors = new HashSet<>(actualAuthorsList);
        assertEquals(actualAuthors.size(), actualAuthorsList.size());
        assertEquals(expectedAuthors, actualAuthors);
    }

    @DisplayName("should delete entity")
    @Test
    void delete() {
        authorRepository.deleteById(FIRST_AUTHOR_ID);
        assertNull(entityManager.find(Author.class, FIRST_AUTHOR_ID));
    }
}