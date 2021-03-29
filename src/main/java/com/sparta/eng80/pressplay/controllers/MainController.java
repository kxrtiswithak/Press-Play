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

    private ActorService actorService;

    public MainController(FilmService filmService, ActorService actorService) {
        this.filmService = filmService;
        this.actorService = actorService;
    }

//    @GetMapping("/")
//    public String welcome(ModelMap modelMap){
//        Iterable<FilmEntity> films = filmService.findTopNMostRentedFilms(3);
//        modelMap.addAttribute("TopFilms", films);
//        return "index";
//    }

    @GetMapping("/")
    public String welcome(Model model){
        Iterable<CategoryEntity> categories = filmService.findAllGenres();
        Iterable<ActorEntity> actors = actorService.getAllActorsAlphabetically();


        Iterable<FilmEntity> topFilms = filmService.findTopNMostRentedFilms(3);
        model.addAttribute("TopFilms", topFilms);

        model.addAttribute("categories", categories);
        model.addAttribute("actors", actors);
        return "index";
    }

    @GetMapping("/index")
    public String index() { return "index"; }
}
