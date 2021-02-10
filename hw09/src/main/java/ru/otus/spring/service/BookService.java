package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.GenreRepository;
import java.util.List;



@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;


    public Book getById(long id) {

        return bookRepository.getOne(id);
    }


    public String getByName(String name) {

        return bookRepository.findByName(name).toString();
    }

    public Book addBook(String name) {
        final Book book = new Book(name);
        final Book savedBook = bookRepository.save(book);
        return savedBook;
    }


    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public void delete(long id) {

        bookRepository.deleteById(id);
    }

    @Transactional
    public Book addAuthor(long bookId, long authorId) {
        Book book = bookRepository.getOne(bookId);
        Author author = authorRepository.getOne(authorId);
        book.getAuthor().add(author);
        return book;
    }

    @Transactional
    public Book removeAuthor(long bookId, long authorId) {
        Book book = bookRepository.getOne(bookId);
        Author author = authorRepository.getOne(authorId);
        book.getAuthor().remove(author);
        return book;
    }

    @Transactional
    public Book addGenre(long bookId, long genreId) {
        Book book = bookRepository.getOne(bookId);
        Genre genre = genreRepository.getOne(genreId);
        book.getGenre().add(genre);
        return book;
    }

    @Transactional
    public Book removeGenre(long bookId, long genreId) {
        Book book = bookRepository.getOne(bookId);
        Genre genre = genreRepository.getOne(genreId);
        book.getGenre().remove(genre);
        return book;
    }
}
