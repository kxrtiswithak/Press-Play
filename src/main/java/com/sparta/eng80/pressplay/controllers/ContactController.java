package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.StaffEntity;
import com.sparta.eng80.pressplay.services.ActorService;
import com.sparta.eng80.pressplay.services.FilmService;
import com.sparta.eng80.pressplay.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {
    private final FilmService filmService;
    private final ActorService actorService;
    private final StaffService staffService;

    @Autowired
    public ContactController(FilmService filmService, ActorService actorService, StaffService staffService) {
        this.filmService = filmService;
        this.actorService = actorService;
        this.staffService = staffService;
    }

    @GetMapping("/contact")
    public String showContactDetails(ModelMap modelMap){
        modelMap = Filters.getFilters(modelMap, filmService, actorService);
        StaffEntity staffMember = staffService.findAll().iterator().next();
        modelMap.addAttribute("staff", staffMember);
        return "fragments/contact";
    }
}
