package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.CategoryEntity;
import com.sparta.eng80.pressplay.entities.FilmEntity;
import com.sparta.eng80.pressplay.services.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final FilmService filmService;

    public MainController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/")
    public String welcome(ModelMap modelMap){
        Iterable<FilmEntity> films = filmService.findTopNMostRentedFilms(3);
        modelMap.addAttribute("TopFilms", films);
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
