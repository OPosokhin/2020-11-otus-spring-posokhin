package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository repository;

    public String getById( Long id) {

        return repository.findById(id).toString();
    }

    public String getByName(String name) {

        return repository.findByName(name).toString();
    }

    public String addGenre(String name) {
        final Genre genre = new Genre(name);
        final Genre savedGenre = repository.save(genre);
        return savedGenre.toString();
    }

    public List<Genre> getAll() {

        return repository.findAll();
    }

    public void delete(long id) {

        repository.deleteById(id);
    }
}
