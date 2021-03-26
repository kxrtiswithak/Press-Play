package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.ActorEntity;
import com.sparta.eng80.pressplay.entities.CategoryEntity;
import com.sparta.eng80.pressplay.entities.FilmEntity;
import com.sparta.eng80.pressplay.services.ActorService;
import com.sparta.eng80.pressplay.services.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private FilmService filmService;
    private ActorService actorService;

    public MainController(FilmService filmService, ActorService actorService) {
        this.filmService = filmService;
        this.actorService = actorService;
    }

    @GetMapping("/")
    public String welcome(Model model){
        Iterable<FilmEntity> films = filmService.findAll();
        Iterable<CategoryEntity> categories = filmService.findAllGenres();
        Iterable<ActorEntity> actors = actorService.getAllActorsAlphabetically();

        List<FilmEntity> results = new ArrayList<>();
//        for (FilmEntity film : films) {
//            if (film.getTitle().contains(title.toUpperCase())) {
//                results.add(film);
//            }
//        }

        model.addAttribute("categories", categories);
        model.addAttribute("actors", actors);
        model.addAttribute("films", results);
        return "index";
    }

    @GetMapping("/index")
    public String index() { return "index"; }

    @PostMapping("/newCategory")
    public String newCategory(@ModelAttribute("category") CategoryEntity category){
//        CategoryEntity categoryEntity = new CategoryEntity();
//        categoryEntity.setName(category.name);
        category = new CategoryEntity();
        return "redirect:/categories";
    }
}
