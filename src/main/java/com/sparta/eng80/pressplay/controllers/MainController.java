package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.CategoryEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Date;

@Controller
public class MainController {

    @GetMapping("/")
    public String welcome(){ return "index";}

    @GetMapping("/index")
    public String index() { return "index"; }

    @PostMapping("/newCategory")
    public String newCategory(@RequestParam("name") String name){
//        CategoryEntity categoryEntity = new CategoryEntity();
//        categoryEntity.setName(category.name);
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(name);
        Date date = new Date();
        categoryEntity.setLastUpdate(new Timestamp(date.getTime()));
        return "redirect:/categories";
    }
}
