package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.domain.Author;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Author dao test")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(AuthorDaoJdbcImpl.class)
class AuthorDaoJdbcImplTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    void count() {
        long oldQuantity = authorDao.count();
        authorDao.insert(new Author(1234567890, "author"));
        long newQuantity = authorDao.count();
        assertEquals(oldQuantity + 1, newQuantity);
    }

    @Test
    void insert() {
        Author author = new Author(1234567890, "New Author");
        authorDao.insert(author);
        Author found = authorDao.getById(1234567890);
        assertEquals("New Author", found.getName());
    }

    @Test
    void getById() {
        Author author = authorDao.getById(2);
        assertEquals("J.K.Rowling", author.getName());
    }

    @Test
    void getByName() {
        List<Author> author = authorDao.getByName("Alan Alexander Milne");
        assertEquals(1, author.size());
        assertEquals(1, author.get(0).getId());
    }

    @Test
    void getAll() {
        List<Author> found = authorDao.getAll();
        assertEquals(authorDao.count(), found.size());
    }

    @Test
    void delete() {
        authorDao.delete(1);
        assertThrows(EmptyResultDataAccessException.class, () -> authorDao.getById(1));
    }
}