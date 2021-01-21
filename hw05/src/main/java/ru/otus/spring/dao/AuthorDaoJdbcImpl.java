package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbcImpl implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;
    private final AuthorMapper mapper = new AuthorMapper();

    @Autowired
    public AuthorDaoJdbcImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
    }

    @Override
    public long count() {

        return jdbc.getJdbcOperations().queryForObject("select count(*) from author", Long.class);
    }

    @Override
    public void insert(Author author) {
        jdbc.update("insert into author (id, name) values (:id, :name)", Map.of("id", author.getId(), "name", author.getName()));
    }

    @Override
    public Author getById(long id) {
        return jdbc.queryForObject("select * from author where id = :id", Map.of("id", id), this.mapper);
    }

    @Override
    public List<Author> getByName(String name) {
        return jdbc.query("select * from author where name = :name", Map.of("namw", name), this.mapper);
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from author", this.mapper);
    }

    @Override
    public boolean delete(long id) {
        return jdbc.update("delete from author where id=:id", Map.of("id", id)) > 0;
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }
}
