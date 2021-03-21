package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.domain.Book;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Book repository test")
@DataJpaTest
class BookRepositoryTest {
    public static final long FIRST_BOOK_ID = 1;
    public static final int EXPECTED_QUERIES_COUNT_PER_BOOK = 1;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TestEntityManager entityManager;

    @DisplayName("should count all entities")
    @Test
    void count() {
        long expectedQuantity = entityManager.getEntityManager().createQuery("select b from Book b", Book.class).
                getResultList().size();
        long actualQuantity = bookRepository.count();
        assertEquals(expectedQuantity, actualQuantity);
    }

    @DisplayName("should generate id on save")
    @Test
    void saveIdTest() {
        Book newBook = new Book();
        newBook.setName("new book");
        bookRepository.save(newBook);
        assertNotEquals(0, newBook.getId());
    }

    @DisplayName("should persist entity")
    @Test
    void save() {
        Book newBook = new Book();
        newBook.setName("new book");
        bookRepository.save(newBook);
        Book foundAuthor = entityManager.find(Book.class, newBook.getId());
        assertEquals(newBook, foundAuthor);
    }

    @DisplayName("should find entity by name")
    @Test
    void findByName() {
        Book expectedBook = entityManager.find(Book.class, FIRST_BOOK_ID);
        Book actualBook = bookRepository.findByName(expectedBook.getName()).get(0);
        assertEquals(expectedBook, actualBook);
    }

    @DisplayName("should find all entities")
    @Test
    void findAll() {
        Set<Book> expectedBooks = new HashSet<>(entityManager.getEntityManager().createQuery("select b from Book b", Book.class).
                getResultList());
        List<Book> actualBookList = bookRepository.findAll();
        Set<Book> actualBooks = new HashSet<>(actualBookList);
        assertEquals(actualBooks.size(), actualBookList.size());
        assertEquals(expectedBooks, actualBooks);
    }

    @DisplayName("should delete entity")
    @Test
    void delete() {
        bookRepository.deleteById(FIRST_BOOK_ID);
        assertNull(entityManager.find(Book.class, FIRST_BOOK_ID));
    }

}