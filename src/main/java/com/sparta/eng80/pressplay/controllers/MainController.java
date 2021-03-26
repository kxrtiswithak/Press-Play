package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.ActorEntity;
import com.sparta.eng80.pressplay.entities.ActorEntity;
import com.sparta.eng80.pressplay.entities.CategoryEntity;
import com.sparta.eng80.pressplay.entities.FilmEntity;
import com.sparta.eng80.pressplay.services.FilmService;
import com.sparta.eng80.pressplay.entities.FilmEntity;
import com.sparta.eng80.pressplay.services.ActorService;
import com.sparta.eng80.pressplay.services.FilmService;
import com.sparta.eng80.pressplay.entities.FilmEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Date;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private final FilmService filmService;

    public MainController(FilmService filmService) {
        this.filmService = filmService;
    }

    private FilmService filmService;
    private ActorService actorService;

    public MainController(FilmService filmService, ActorService actorService) {
        this.filmService = filmService;
        this.actorService = actorService;
    }

    @GetMapping("/")
    public String welcome(ModelMap modelMap){
        Iterable<FilmEntity> films = filmService.findTopNMostRentedFilms(3);
        modelMap.addAttribute("TopFilms", films);
        return "index";
    }
//
//    @GetMapping("/")
//    public String welcome(Model model){
//        Iterable<FilmEntity> films = filmService.findAll();
//        Iterable<CategoryEntity> categories = filmService.findAllGenres();
//        Iterable<ActorEntity> actors = actorService.getAllActorsAlphabetically();
//
//        List<FilmEntity> results = new ArrayList<>();
////        for (FilmEntity film : films) {
////            if (film.getTitle().contains(title.toUpperCase())) {
////                results.add(film);
////            }
////        }
//
//        model.addAttribute("categories", categories);
//        model.addAttribute("actors", actors);
//        model.addAttribute("films", results);
//        return "index";
//    }

    @GetMapping("/index")
    public String index() { return "index"; }


}
