package ru.otus.spring.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.spring.domain.Author;
import ru.otus.spring.service.AuthorService;

@Controller
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {

        this.authorService = authorService;
    }

    @GetMapping("/author")
    public String authorsPage(Model model) {
        model.addAttribute("author", authorService.getAll());
        model.addAttribute("authorToAdd", new Author());
        return "author";
    }

    @PostMapping("/author/{authorId}/delete")
    public String delete(@PathVariable long authorId) {
        authorService.delete(authorId);
        return "redirect:/author";
    }

    @PostMapping("/author")
    public String add(@ModelAttribute("authorToAdd") Author authorToAdd) {
        authorService.addAuthor(authorToAdd.getName());
        return "redirect:/author";
    }
}
