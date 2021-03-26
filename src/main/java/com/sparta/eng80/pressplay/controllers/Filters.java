package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.ActorEntity;
import com.sparta.eng80.pressplay.entities.CategoryEntity;
import com.sparta.eng80.pressplay.services.ActorService;
import com.sparta.eng80.pressplay.services.FilmService;
import org.springframework.ui.ModelMap;

public class Filters {

    public static ModelMap getFilters(ModelMap model, FilmService filmService, ActorService actorService) {
        Iterable<CategoryEntity> categories = filmService.findAllGenres();
        Iterable<ActorEntity> actors = actorService.getAllActorsAlphabetically();

        model.addAttribute("categories", categories);
        model.addAttribute("actors", actors);
        return model;
    }
}
