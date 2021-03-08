package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;


@RestController
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentService commentService;
    private final BookService bookService;

    @DeleteMapping("/api/comment/{id}")
    public void updateBook(@PathVariable Long id) {
        commentService.getById(id);
    }

    @PostMapping("/api/bookEdit/{bookId}/comment")
    public Long newComment(@PathVariable Long bookId, @RequestParam String text) {
        Comment c= new Comment(null, bookService.getById(bookId), text);
        c = commentService.save(c);
        return c.getId();
    }
}
