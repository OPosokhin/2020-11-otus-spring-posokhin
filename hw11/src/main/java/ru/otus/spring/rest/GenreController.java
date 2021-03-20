package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepository;

@RequiredArgsConstructor
@RestController
public class GenreController {

    private final GenreRepository genreRepository;
    
    @GetMapping("api/genre")
    public Flux<Genre> findAllGenres() {

        return genreRepository.findAll();
    }

}
