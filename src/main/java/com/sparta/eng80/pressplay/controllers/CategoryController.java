package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.CategoryEntity;
import com.sparta.eng80.pressplay.entities.FilmEntity;
import com.sparta.eng80.pressplay.services.ActorService;
import com.sparta.eng80.pressplay.services.CategoryService;
import com.sparta.eng80.pressplay.services.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryController {
    private final FilmService filmService;
    private final CategoryService categoryService;
    private final ActorService actorService;

    public CategoryController(FilmService filmService, CategoryService categoryService, ActorService actorService) {
        this.filmService = filmService;
        this.categoryService = categoryService;
        this.actorService = actorService;
    }

    @GetMapping("/categories")
    public String findCategories(ModelMap modelMap){
        modelMap = Filters.getFilters(modelMap, filmService, actorService);
        Iterable<CategoryEntity> categories = filmService.findAllGenres();
        List<Integer> categorySizes = new ArrayList<>();
        for (CategoryEntity category : categories) {
            categorySizes.add((int)filmService.findByCategory(category.getCategoryId()).spliterator().getExactSizeIfKnown());
        }
        modelMap.addAttribute("sizes", categorySizes);
        return "fragments/categories";
    }

    @GetMapping("/category")
    public String findCategory(@RequestParam("category") String category, ModelMap modelMap){
        Iterable<FilmEntity> filmEntities = filmService.findByCategory(category);
        modelMap.addAttribute("films", filmEntities);
        return "fragments/results";
    }

    @PostMapping("/newCategory")
    public String newCategory(@RequestParam("name") String name){
        categoryService.addCategory(name);
        return "redirect:/categories";
    }
}
