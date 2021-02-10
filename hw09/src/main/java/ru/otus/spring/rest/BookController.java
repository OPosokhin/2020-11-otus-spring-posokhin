package ru.otus.spring.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.BookService;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/book")
    public String bookPage(Model model) {
        model.addAttribute("book", bookService.getAll());
        model.addAttribute("bookToAdd", new Book());
        return "book";
    }


    @PostMapping("/book/{bookId}/delete")
    public String delete(@PathVariable long bookId) {
        bookService.delete(bookId);
        return "redirect:/book";
    }

    @PostMapping("/book")
    public String add(@ModelAttribute("bookToAdd") Book bookToAdd) {
        bookService.addBook(bookToAdd.getName());
        return "redirect:/book";
    }
}
