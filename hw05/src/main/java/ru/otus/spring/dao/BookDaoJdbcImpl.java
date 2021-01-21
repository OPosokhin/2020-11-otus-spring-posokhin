package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbcImpl implements BookDao {
    private final NamedParameterJdbcOperations jdbc;
    private final BookExtractor extractor = new BookExtractor();

    @Override
    public long count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from book", Long.class);
    }

    @Override
    public void insert(Book book) {
        jdbc.update("insert into book (id, name) values (:id, :name)", Map.of("id", book.getId(), "name", book.getName()));
        for (Genre genre : book.getGenres()) {
            jdbc.update("insert into book_genre (book_id, genre_id) values (:book_id, :genre_id)", Map.of("book_id", book.getId(), "genre_id", genre.getId()));
        }
        for (Author author : book.getAuthors()) {
            jdbc.update("insert into book_author (book_id, author_id) values (:book_id, :author_id)", Map.of("book_id", book.getId(), "author_id", author.getId()));
        }
    }

    @Override
    public Book getById(long id) {
        Map<Long, Book> found = jdbc.query("select book.name as book_name, book.id as book_id, " +
                "author.id as author_id, author.name as author_name, " +
                "genre.id as genre_id, genre.name as genre_name from book " +
                "left join book_author on book.id = book_author.book_id " +
                "left join author on book_author.author_id = author.id " +
                "left join book_genre on book.id = book_genre.book_id " +
                "left join genre on book_genre.genre_id = genre.id where book.id=:id", Map.of("id", id), extractor);
        return found.getOrDefault(id, null);
    }

    @Override
    public List<Book> getByName(String name) {
        Map<Long, Book> found = jdbc.query("select book.name as book_name, book.id as book_id, " +
                "author.id as author_id, author.name as author_name, from book " +
                "left join book_author on book.id = book_author.book_id " +
                "left join author on book_author.author_id = author.id " +
                "left join book_genre on book.id = book_genre.book_id " +
                "left join genre on book_genre.genre_id = genre.id where book.name=:name", Map.of("name", name), extractor);
        return new ArrayList<>(found.values());
    }

    @Override
    public List<Book> getAll() {
        Map<Long, Book> found = jdbc.query("select book.name as book_name, book.id as book_id, author.id as author_id, author.name as author_name,"+
                "genre.id as genre_id, genre.name as genre_name from book " +
                "left join book_author on book.id = book_author.book_id " +
                "left join author on book_author.author_id = author.id " +
                "left join book_genre on book.id = book_genre.book_id " +
                "left join genre on book_genre.genre_id = genre.id", new BookExtractor());
        return new ArrayList<>(found.values());
    }

    private static class BookExtractor implements ResultSetExtractor<Map<Long, Book>> {
        public Map<Long, Book> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Map<Long, Book> books = new HashMap<>();
            while (resultSet.next()) {
                final Long bookId = resultSet.getLong("book_id");
                final String name = resultSet.getString("book_name");
                Set<Genre> genres;
                Set<Author> authors;
                final Book oldBook = books.get(bookId);
                if (oldBook != null) {
                    genres = oldBook.getGenres();
                    authors = oldBook.getAuthors();
                } else {
                    genres = new HashSet<>();
                    authors = new HashSet<>();
                }
                Long genreId = resultSet.getObject("genre_id", Long.class);
                if (genreId != null) {
                    final String genreName = resultSet.getString("genre_name");
                    final Genre genre = new Genre(genreId, genreName);
                    genres.add(genre);
                }
                Long authorId = resultSet.getObject("author_id", Long.class);
                if (authorId != null) {
                    String authorName = resultSet.getString("author_name");
                    final Author author = new Author(authorId, authorName);
                    authors.add(author);
                }
                final Book newBook = new Book(bookId, name, authors, genres);
                books.put(bookId, newBook);
            }
            return books;
        }
    }

    @Override
    public boolean delete(long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbc.update("delete from book where id=:id; delete from book_author where book_id=:id", params) > 0;
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Book(id, name, null, null);

        }
    }
}
