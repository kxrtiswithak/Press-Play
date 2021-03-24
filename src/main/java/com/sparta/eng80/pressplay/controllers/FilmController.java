package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.FilmEntity;
import com.sparta.eng80.pressplay.services.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }
/*
    @GetMapping
    public String findByTitle(String title, ModelMap modelMap){
        Iterable<FilmEntity> filmEntities = filmService.findByTitle(title);
        modelMap.addAttribute("films", filmEntities);
        return null; //TODO
    }

    @GetMapping
    public String findActor(String name, ModelMap modelMap){
        Iterable<FilmEntity> filmEntities = filmService.findActorByName(name);
        modelMap.addAttribute("films", filmEntities);
        return null; //TODO
    }

    @GetMapping
    public String findActor(String firstName, String lastName, ModelMap modelMap){
        Iterable<FilmEntity> filmEntities = filmService.findActorByName(firstName, lastName);
        modelMap.addAttribute("films", filmEntities);
        return null; //TODO
    }

    @GetMapping
    public String findByActorId(int id, ModelMap modelMap){
        Iterable<FilmEntity> filmEntities = (Iterable<FilmEntity>) filmService.findActorById(id).stream().iterator();
        modelMap.addAttribute("films", filmEntities);
        return null; //TODO
    }

    @GetMapping
    public String findByLanguage(String language, ModelMap modelMap){
        Iterable<FilmEntity> filmEntities = filmService.findByLanguage(language);
        modelMap.addAttribute("films", filmEntities);
        return null; //TODO
    }

 */
}
