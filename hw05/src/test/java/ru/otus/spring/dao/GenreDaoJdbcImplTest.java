package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.domain.Genre;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Genre dao test")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(GenreDaoJdbcImpl.class)
class GenreDaoJdbcImplTest {

    @Autowired
    private GenreDaoJdbcImpl genreDaoJdbcImpl;

    @Test
    void count() {
        long oldQuantity = genreDaoJdbcImpl.count();
        genreDaoJdbcImpl.insert(new Genre(1234567890, "genre"));
        long newQuantity = genreDaoJdbcImpl.count();
        assertEquals(oldQuantity + 1, newQuantity);

    }

    @Test
    void getAll() {
        List<Genre> found = genreDaoJdbcImpl.getAll();
        assertEquals(genreDaoJdbcImpl.count(), found.size());
    }

    @DisplayName(" should find proper genre")
    @Test
    void getById() {
        Genre genre = genreDaoJdbcImpl.getById(1);
        assertEquals(genre.getName(), "Classic");
    }

    @DisplayName(" should find genre by name")
    @Test
    void getByName() {
        List<Genre> genres = genreDaoJdbcImpl.getByName("Detective");
        assertEquals(3, genres.size());
        assertEquals(3, genres.get(0).getId());
    }

    @Test
    void insert() {
        Genre genre = new Genre(1234567890, "New book");
        genreDaoJdbcImpl.insert(genre);
        Genre found = genreDaoJdbcImpl.getById(1234567890);
        assertEquals("New book", found.getName());
    }

    @Test
    void delete() {
        genreDaoJdbcImpl.delete(1);
        assertThrows(EmptyResultDataAccessException.class, () -> genreDaoJdbcImpl.getById(1));
    }
}