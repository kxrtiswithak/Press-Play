package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.ActorEntity;
import com.sparta.eng80.pressplay.entities.CategoryEntity;
import com.sparta.eng80.pressplay.entities.FilmEntity;
import com.sparta.eng80.pressplay.services.ActorService;
import com.sparta.eng80.pressplay.services.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    private FilmService filmService;
    private ActorService actorService;

    public SearchController(FilmService filmService, ActorService actorService) {
        this.filmService = filmService;
        this.actorService = actorService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model, @RequestParam("title") String title) {
//        @RequestParam("category") String category, @RequestParam("actor") String actor
        Iterable<FilmEntity> films = filmService.findAll();
        Iterable<CategoryEntity> categories = filmService.findAllGenres();
        Iterable<ActorEntity> actors = actorService.getAllActorsAlphabetically();

        List<FilmEntity> results = new ArrayList<>();
        for (FilmEntity film : films) {
            if (film.getTitle().contains(title.toUpperCase())) {
                results.add(film);
            }
        }

        model.addAttribute("categories", categories);
        model.addAttribute("actors", actors);
        model.addAttribute("films", results);
        return "/fragments/results";
    }
}
