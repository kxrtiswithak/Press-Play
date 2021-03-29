package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.FilmEntity;
import com.sparta.eng80.pressplay.services.ActorService;
import com.sparta.eng80.pressplay.services.FilmService;
import com.sparta.eng80.pressplay.services.InventoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FilmDetailsController {

    private final FilmService filmService;
    private final ActorService actorService;
    private final InventoryService inventoryService;

    public FilmDetailsController(FilmService filmService, ActorService actorService, InventoryService inventoryService) {
        this.filmService = filmService;
        this.actorService = actorService;
        this.inventoryService = inventoryService;
    }

    @GetMapping("/film")
    public String addFilm(ModelMap modelMap, @RequestParam("id") int id){
        modelMap = Filters.getFilters(modelMap, filmService, actorService);
        FilmEntity film = filmService.findById(id).get();
        modelMap.addAttribute("film", film);
        int stock = inventoryService.getNumberInStockByFilmId(id);
        modelMap.addAttribute("stock", stock);
        return "fragments/film_details";
    }
}
