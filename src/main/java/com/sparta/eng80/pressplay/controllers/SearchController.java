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
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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
        System.out.println(films.toString());

        // Future Improvements
        // Array of 26 arrays - (1 for each character in alphabet)
        // When iterating through put the film into the correct array based on the first letter
        // then do arrays.sort() on the array that was added to

        for (FilmEntity film : films) {
            // Add all films that start with the search string first
            if (film.getTitle().toUpperCase().startsWith(title.toUpperCase())) {
                results.add(0, film);
            } else {
                // Add all films which have the search string in the title
                if (film.getTitle().toUpperCase().contains(title.toUpperCase())) {
                    results.add(film);
                }
            }
         }

        model.addAttribute("categories", categories);
        model.addAttribute("actors", actors);
        model.addAttribute("films", results);
        return "/fragments/results";
    }
}
