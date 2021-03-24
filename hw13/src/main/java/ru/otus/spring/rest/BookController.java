package ru.otus.spring.rest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.GenreService;

@Controller
public class BookController {
    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;


    public BookController(BookService bookService, GenreService genreService, AuthorService authorService) {
        this.bookService = bookService;
        this.genreService = genreService;
        this.authorService = authorService;

    }


    @GetMapping("/book")
    public String bookPage(Model model) {
        model.addAttribute("book", bookService.getAll());
        return "book";
    }


    @PostMapping("/bookAdd")
    public String addBook(Model model) {
        Book book = new Book();
        book.setId(0l);
        model.addAttribute("book", book);
        model.addAttribute("author", authorService.getAll());
        model.addAttribute("genre", genreService.getAll());
        return "bookAdd";
    }


    @GetMapping("/edit/{id}")
    public String getBook(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("author", authorService.getAll());
        model.addAttribute("genre", genreService.getAll());
        model.addAttribute("book", book);
        return "bookEdit";
    }


    @PostMapping("/book/{bookId}/delete")
    public String delete(@PathVariable long bookId) {
        bookService.delete(bookId);
        return "redirect:/book";
    }

}

