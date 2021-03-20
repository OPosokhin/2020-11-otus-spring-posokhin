package ru.otus.spring.rest;


import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.rest.dto.AuthorDto;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.rest.dto.GenreDto;

import java.util.function.Function;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;


@RequiredArgsConstructor
@RestController
public class BookController {
//    @Bean
//    public RouterFunction<ServerResponse> bookRoutes(BookRepository bookRepository, ModelMapper modelMapper, AuthorRepository authorRepository, GenreRepository genreRepository) {
//        final Function<Object, BookDto> bookInfoToDtoMapper = book -> modelMapper.map(book, BookDto.class);
//        final Function<Author, AuthorDto> authorToDtoMapper = (Author author) -> modelMapper.map(author, AuthorDto.class);
//        final Function<Genre, GenreDto> genreToDtoMapper = (Genre genre) -> modelMapper.map(genre, GenreDto.class);
//        return route()
//                .GET("/api/book",
//                        request -> ok().contentType(APPLICATION_JSON)
//                                .body(bookRepository.findAllBookInfosBy()
//                                        .map(bookInfoToDtoMapper), BookDto.class))

    private final BookRepository bookRepository;

    @GetMapping("/api/book")
    public Flux<Book> findBook() {
        return bookRepository.findAll();
    }
//
//    .GET("/api/books",
//         request -> ok().contentType(APPLICATION_JSON)
//                                .body(bookRepository.findAllBookInfosBy()
//                                        .map(bookInfoToDtoMapper), BookDto.class))
//
    @GetMapping("/api/book/{id}")
    public Mono<Book> findBookById(@PathVariable(name = "id") String id) {
        return bookRepository.findById(id);
    }

    @PostMapping(value = "/api/book", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Book> createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/api/book/{id}")
    public Mono<Void> deleteBookById(@PathVariable(name = "id") String id) {
        return bookRepository.deleteById(id);
    }
    }

