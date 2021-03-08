package ru.otus.spring.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.GenreService;

@Controller
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genre")
    public String genresPage(Model model) {
        model.addAttribute("genre", genreService.getAll());
        model.addAttribute("genreToAdd", new Genre());
        return "genre";
    }

    @PostMapping("/genre")
    public String add(@ModelAttribute("genreToAdd") Genre authorToAdd) {
        genreService.addGenre(authorToAdd.getName());
        return "redirect:/genre";
    }
}
