package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.CustomerEntity;
import com.sparta.eng80.pressplay.entities.FilmEntity;
import com.sparta.eng80.pressplay.entities.UserEntity;
import com.sparta.eng80.pressplay.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Controller
public class RentalController {

    private final RentalService rentalService;
    private final FilmService filmService;
    private final ActorService actorService;
    private final CustomerService customerService;
    private final StaffService staffService;
    private final SecurityService securityService;

    public RentalController(RentalService rentalService, FilmService filmService, ActorService actorService, CustomerService customerService, StaffService staffService, SecurityService securityService) {
        this.rentalService = rentalService;
        this.filmService = filmService;
        this.actorService = actorService;
        this.customerService = customerService;
        this.staffService = staffService;
        this.securityService = securityService;
    }

    @GetMapping("/rent")
    public String rent(){
        return "fragments/rent";
    }

    @PostMapping("/rent")
    public String rentFilm(ModelMap model, @RequestParam(value = "film") Integer filmId){
        Filters.getFilters(model, filmService, actorService);
        Optional<FilmEntity> filmEntity = filmService.findById(filmId);
        FilmEntity film = filmEntity.orElse(null);
        Date currentDate = new Date();
        Calendar returnDate = Calendar.getInstance();
        returnDate.setTime(currentDate);
        returnDate.add(Calendar.DAY_OF_MONTH, film.getRentalDuration());
        UserEntity user = securityService.getCurrentUser();
        if (user instanceof CustomerEntity) {
            CustomerEntity customer = (CustomerEntity) user;
            rentalService.rentAFilm(film, customer, returnDate.getTime(), staffService.findById(1).orElse(null));
        }
        return "/fragments/rent";
    }
}
