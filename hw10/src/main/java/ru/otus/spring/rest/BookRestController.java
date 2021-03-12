package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.GenreService;


@RestController
@RequiredArgsConstructor
public class BookRestController {
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;

    @PatchMapping("/api/bookEdit/{id}")
    public void updateBook(@PathVariable Long id, @RequestParam String name,@RequestParam Long authorid,@RequestParam Long genreid) {
        Book book = bookService.getById(id);
        book.setName(name);
        book.setAuthor(authorService.getById(authorid));
        book.setGenre(genreService.getById(genreid));
        bookService.save(book);
    }

    @PostMapping("/api/bookAdd/{id}")
    public void addBook(@RequestParam String name,@RequestParam Long authorid,@RequestParam Long genreid) {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(authorService.getById(authorid));
        book.setGenre(genreService.getById(genreid));
        bookService.save(book);
    }

    @DeleteMapping("/api/bookEdit/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }
}
