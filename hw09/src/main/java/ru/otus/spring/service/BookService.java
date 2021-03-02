package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repository.BookRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book getById(long id) {

        return bookRepository.getOne(id);
    }

    public Book save(Book book) {

        return bookRepository.save(book);
    }

    public String getByName(String name) {

        return bookRepository.findByName(name).toString();
    }


    public List<Book> getAll() {

        return bookRepository.findAll();
    }

    public void delete(long id) {

        bookRepository.deleteById(id);
    }

    @Transactional
    public void update(Book book) {
        this.bookRepository.save(book);
    }

}
