package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.ActorEntity;
import com.sparta.eng80.pressplay.entities.CategoryEntity;
import com.sparta.eng80.pressplay.entities.FilmEntity;
import com.sparta.eng80.pressplay.services.ActorService;
import com.sparta.eng80.pressplay.services.FilmService;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class SearchController {
    private FilmService filmService;
    private ActorService actorService;

    public SearchController(FilmService filmService, ActorService actorService) {
        this.filmService = filmService;
        this.actorService = actorService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(Model model, @RequestParam(value = "title") String title, @RequestParam(value = "category", defaultValue = "NONE") String category, @RequestParam(value = "actor", defaultValue = "NONE") String actor) {
        Iterable<FilmEntity> films  = filmService.findAll();
        Iterable<CategoryEntity> categories = filmService.findAllGenres();
        Iterable<ActorEntity> actors = actorService.getAllActorsAlphabetically();

        HashSet<FilmEntity> results = new HashSet<>();
        String[] selectedCategories = category.split(",");
        String[] selectedActors = actor.split(",");
        System.out.println(Arrays.toString(selectedActors));

        // Future Improvements
        // Array of 26 arrays - (1 for each character in alphabet)
        // When iterating through put the film into the correct array based on the first letter
        // then do arrays.sort() on the array that was added to

        // All films by actor
        if ((title.isEmpty() && category.equals("NONE")) && !actor.equals("NONE")) {
            for (String actorName : selectedActors) {
                results.addAll((Collection<? extends FilmEntity>) filmService.findActorByName(actorName));
            }
        }

        // If title was not entered and filtering by category
        if (title.isEmpty() && (!category.equals("NONE") && selectedCategories.length != 0)) {
            // Searching by category only
            for (String categoryName : selectedCategories) {
                results.addAll((Collection<? extends FilmEntity>) filmService.findByCategory(categoryName));
            }
        } else {
            // Title is present
            for (FilmEntity film : films) {
                // Add all films that start with the search string first
                if (film.getTitle().toUpperCase().startsWith(title.toUpperCase())) {
                    results.add(film);
                } else {
                    // Add all films which have the search string in the title
                    if (film.getTitle().toUpperCase().contains(title.toUpperCase())) {
                        results.add(film);
                    }
                }
            }

            if (!category.equals("NONE") && selectedCategories.length != 0) {
                results = matchByCategories(results, selectedCategories);
            }
        }

        if (!actor.equals("NONE")) {
            results = matchByActors(results, selectedActors);
        }

        model.addAttribute("categories", categories);
        model.addAttribute("actors", actors);
        model.addAttribute("films", results);
        return "/fragments/results";
    }

    public HashSet<FilmEntity> matchByActors(HashSet<FilmEntity> films, String[] selectedActors) {
        Iterator<FilmEntity> it = films.iterator();
        while (it.hasNext()) {
            FilmEntity film = it.next();
            List<String> filmActors = new ArrayList<>();
            for (ActorEntity actorEntity : film.getActors()) {
                filmActors.add(actorEntity.getFirstName() + " " + actorEntity.getLastName());
            }

            boolean match = false;
            for (String actorName : selectedActors) {
                match = filmActors.contains(actorName);
                if (match) {
                    break;
                }
            }
            if (!match) {
                it.remove();
            }

        }
        return films;
    }

    public HashSet<FilmEntity> matchByCategories(HashSet<FilmEntity> films, String[] selectedCategories) {
        Iterator<FilmEntity> it = films.iterator();
        while (it.hasNext()) {
            FilmEntity film = it.next();
            List<String> filmCategories = new ArrayList<>();
            for (CategoryEntity categoryEntity : film.getCategories()) {
                filmCategories.add(categoryEntity.getName());
            }

            boolean match = false;
            for (String categoryName : selectedCategories) {
                match = filmCategories.contains(categoryName);
                if (match) {
                    break;
                }
            }
            if (!match) {
                it.remove();
            }
        }
        return films;
    }


}
