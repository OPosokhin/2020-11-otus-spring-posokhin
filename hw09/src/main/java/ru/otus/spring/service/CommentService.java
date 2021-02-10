package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    public String getById(long id) {

        return commentRepository.findById(id).toString();
    }

    @Transactional(readOnly = true)
    public String getByBookId( long id) {
        Book book = bookRepository.findById(id).get();
        return book.getComment().toString();
    }

    public String addComment( long id, String text) {
        return commentRepository.save(new Comment(text, new Book(id))).toString();
    }

    public void delete(long id) {
        commentRepository.deleteById(id);
    }
}
