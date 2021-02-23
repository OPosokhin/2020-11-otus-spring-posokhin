package ru.otus.spring.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
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

    @PostMapping("/book")
    public String add(@ModelAttribute("bookToAdd") Book bookToAdd) {
        bookService.addBook(bookToAdd.getName());
        return "redirect:/book";
    }

    @PostMapping("/book/{bookId}/delete")
    public String delete(@PathVariable long bookId) {
        bookService.delete(bookId);
        return "redirect:/book";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Book book = this.bookService.getById(id);
        model.addAttribute("book", book);
        return "bookEdit";
    }

    @PostMapping("/edit")
    public RedirectView editBook(Book book) {
        this.bookService.update(book);
        return new RedirectView("/book");
    }

}
