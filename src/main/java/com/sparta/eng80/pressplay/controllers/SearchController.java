package com.sparta.eng80.pressplay.controllers;

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
        Iterable<FilmEntity> films = filmService.findAll();

        List<FilmEntity> results = new ArrayList<>();
        for (FilmEntity film : films) {
            if (film.getTitle().contains(title.toUpperCase())) {
                results.add(film);
            }
        }

        model.addAttribute("films", results);
        return "index";
    }
}
