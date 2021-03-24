package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.CategoryEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String welcome(){ return "index";}

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
