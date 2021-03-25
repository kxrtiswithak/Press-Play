package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.ActorEntity;
import com.sparta.eng80.pressplay.entities.CategoryEntity;
import com.sparta.eng80.pressplay.entities.FilmEntity;
import com.sparta.eng80.pressplay.entities.LanguageEntity;
import com.sparta.eng80.pressplay.services.ActorService;
import com.sparta.eng80.pressplay.services.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FilmController {

    private final FilmService filmService;
    private final ActorService actorService;

    @Autowired
    public FilmController(FilmService filmService, ActorService actorService) {
        this.filmService = filmService;
        this.actorService = actorService;
    }

    @RequestMapping("/")
    public String getAllFilms(Model model) {
        Iterable<FilmEntity> filmEntities = filmService.findAll();
        Iterable<CategoryEntity> categoryEntities = filmService.findAllGenres();
        model.addAttribute("categories", categoryEntities);
        model.addAttribute("films", filmEntities);
        return "index";
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

    @GetMapping("/category")
    public String findCategory(@RequestParam("category") String category, ModelMap modelMap){
        Iterable<FilmEntity> filmEntities = filmService.findByCategory(category);
        modelMap.addAttribute("films", filmEntities);
        return "fragments/results";
    }

    @GetMapping("/add-film")
    public String addFilm(ModelMap modelMap){
        Iterable<CategoryEntity> categoryEntities = filmService.findAllGenres();
        modelMap.addAttribute("categories", categoryEntities);
        Iterable<ActorEntity> actorEntities = actorService.getAllActorsAlphabetically();
        modelMap.addAttribute("actors", actorEntities);
        Iterable<LanguageEntity> languageEntities = filmService.findAllLanguages();
        modelMap.addAttribute("languages", languageEntities);
        return "fragments/add-film";
    }

    @PostMapping("/add-film")
    public String addFilm(FilmEntity filmEntity){
        filmService.save(filmEntity);
        return "fragments/add-film";
    }
}
