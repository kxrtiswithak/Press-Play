package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.CategoryEntity;
import com.sparta.eng80.pressplay.entities.FilmEntity;
import com.sparta.eng80.pressplay.services.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Controller
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }


    @GetMapping("/title")
    public String findByTitle(String title, ModelMap modelMap){
        Iterable<FilmEntity> filmEntities = filmService.findByTitle(title);
        modelMap.addAttribute("films", filmEntities);
        return "index";
    }

    @GetMapping("/actor")
    public String findActor(String name, ModelMap modelMap){
        Iterable<FilmEntity> filmEntities = filmService.findActorByName(name);
        modelMap.addAttribute("films", filmEntities);
        return "index";
    }

    @GetMapping("/categories")
    public String findCategories(ModelMap modelMap){
        Iterable<CategoryEntity> categories = filmService.findAllGenres();
        modelMap.addAttribute("categories", categories);
        return "fragments/categories";
    }

    @GetMapping("/categories/{category}")
    public String findCategory(@PathVariable("category") String category, ModelMap modelMap){
        Iterable<FilmEntity> filmEntities = filmService.findByCategory(category);
        modelMap.addAttribute("films", filmEntities);
        return "index";
    }
}
