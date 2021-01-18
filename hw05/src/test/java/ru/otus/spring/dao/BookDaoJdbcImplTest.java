package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.domain.Book;


import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Book dao test")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(BookDaoJdbcImpl.class)
class BookDaoJdbcImplTest {
    @Autowired
    private BookDaoJdbcImpl bookDaoJdbcImpl;

    @Test
    void count() {
        long oldQuantity = bookDaoJdbcImpl.count();
        bookDaoJdbcImpl.insert(new Book(1234567890, "book", Collections.emptySet(), Collections.emptySet()));
        long newQuantity = bookDaoJdbcImpl.count();
        assertEquals(oldQuantity + 1, newQuantity);
    }

    @Test
    void insert() {
        Book book = new Book(1234567890, "New book", Collections.emptySet(), Collections.emptySet());
        bookDaoJdbcImpl.insert(book);
        Book found = bookDaoJdbcImpl.getById(1234567890);
        assertEquals("New book", found.getName());
    }

    @Test
    void getById() {
        Book book = bookDaoJdbcImpl.getById(1);
        System.out.println(book);
        assertEquals(1, book.getId());
        assertEquals("Harry Potter and the Philosophers Stone", book.getName());
        assertEquals(1, book.getAuthors().size());
        assertEquals("J.K.Rowling", book.getAuthors().stream().findFirst().get().getName());
        assertEquals(1, book.getGenres().size());
        assertEquals("Fantastic", book.getGenres().stream().findFirst().get().getName());
    }

    @Test
    void getByName() {
        List<Book> books = bookDaoJdbcImpl.getByName("Harry Potter and the Philosophers Stone");
        assertEquals(1, books.size());
        Book book = books.get(0);
        assertEquals(1, book.getId());
        assertEquals("Harry Potter and the Philosophers Stone", book.getName());
        assertEquals(1, book.getAuthors().size());
        assertEquals("J.K.Rowling", book.getAuthors().stream().findFirst().get().getName());
        assertEquals(1, book.getGenres().size());
        assertEquals("Fantastic", book.getGenres().stream().findFirst().get().getName());
    }

    @Test
    void getAll() {
        List<Book> found = bookDaoJdbcImpl.getAll();
        assertEquals(bookDaoJdbcImpl.count(), found.size());
    }

    @Test
    void delete() {
        bookDaoJdbcImpl.delete(1);
        assertNull(bookDaoJdbcImpl.getById(1));
    }
}