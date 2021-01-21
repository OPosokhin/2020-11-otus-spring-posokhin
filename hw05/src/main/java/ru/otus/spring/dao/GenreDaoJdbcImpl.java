package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbcImpl implements GenreDao {
    private final NamedParameterJdbcOperations jdbc;
    private final GenreMapper mapper = new GenreMapper();

    @Override
    public long count() {
      return jdbc.getJdbcOperations().queryForObject("select count(*) from genre", Long.class);
    }


    @Override
    public void insert(Genre genre) {
        jdbc.update("insert into genre (id, name) values (:id, :name)", Map.of("id", genre.getId(), "name", genre.getName()));
    }

    @Override
    public Genre getById(long id) {
        return jdbc.queryForObject("select * from genre where id = :id", Map.of("id", id), this.mapper);
    }

    @Override
    public List<Genre> getByName(String name) {
        return jdbc.query("select * from genre where name = :name", Map.of("name", name), this.mapper);
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from genre", this.mapper);
    }


    @Override
    public boolean delete(long id) {
        return jdbc.update("delete from genre where id=:id", Map.of("id", id)) > 0;
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
