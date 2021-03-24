package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.CategoryEntity;
import com.sparta.eng80.pressplay.entities.FilmCategoryEntity;
import com.sparta.eng80.pressplay.entities.FilmEntity;
import com.sparta.eng80.pressplay.repositories.CategoryRepository;
import com.sparta.eng80.pressplay.repositories.FilmCategoryRepository;
import com.sparta.eng80.pressplay.repositories.FilmRepository;
import com.sparta.eng80.pressplay.services.interfaces.FilmInterface;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService implements FilmInterface {

    private final FilmRepository filmRepository;
    private final FilmCategoryRepository filmCategoryRepository;
    private final CategoryRepository categoryRepository;

    public FilmService(FilmRepository filmRepository, FilmCategoryRepository filmCategoryRepository, CategoryRepository categoryRepository) {
        this.filmRepository = filmRepository;
        this.filmCategoryRepository = filmCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    public Iterable<FilmEntity> findByCategory(int categoryID) {
        if (categoryID < 0) {
            return new ArrayList<>();
        }
        Iterable<CategoryEntity> AllCategories = categoryRepository.findAll();
        boolean found = false;
        for (CategoryEntity categoryEntity : AllCategories) {
            if (categoryEntity.getCategoryId() == categoryID) {
                categoryID = categoryEntity.getCategoryId();
                found = true;
                break;
            }
        }
        if (!found) {
            // genre doesn't exist, return empty list
            return new ArrayList<>();
        }
        Iterable<FilmCategoryEntity> AllFilmCategorys = filmCategoryRepository.findAll();
        ArrayList<Integer> allFilmIdsThatBelongToGenre = new ArrayList<>();
        for (FilmCategoryEntity filmCategoryEntity : AllFilmCategorys) {
            if (filmCategoryEntity.getCategoryId() == categoryID) {
                allFilmIdsThatBelongToGenre.add(filmCategoryEntity.getFilmId());
            }
        }
        if (allFilmIdsThatBelongToGenre.isEmpty()) {
            // if no films were found, no point continuing
            return new ArrayList<>();
        }
        List<FilmEntity> result = new ArrayList<>();
        Iterable<FilmEntity> AllFilms = findAll();
        for (int filmId : allFilmIdsThatBelongToGenre) {
            for (FilmEntity film : AllFilms) {
                if (film.getFilmId() == filmId) {
                    result.add(film);
                }
            }
        }
        return result;
    }

    public Iterable<FilmEntity> findByCategory(String genre) {
        if (genre == null || genre.equals("")) {
            // return empty list
            return new ArrayList<>();
        }
        Iterable<CategoryEntity> AllCategories = categoryRepository.findAll();
        for (CategoryEntity categoryEntity : AllCategories) {
            if (categoryEntity.getName().equals(genre)) {
                return findByCategory(categoryEntity.getCategoryId());
            }
        }
        return new ArrayList<>();
    }

    public Iterable<CategoryEntity> findAllGenres() {
        return categoryRepository.findAll();
    }

    @Override
    public Iterable<FilmEntity> findByTitle(String title) {
        return filmRepository.findByTitle(title);
    }

    @Override
    public Optional<FilmEntity> findActorById(int actorID) {
        if(actorID < 0){
            return null;
        }
        return filmRepository.findByActor(actorID);
    }

    @Override
    public Iterable<FilmEntity> findActorByName(String actor) {
        actor = "%" + actor + "%";
        return filmRepository.findByName(actor);
    }

    @Override
    public Iterable<FilmEntity> findActorByName(String firstName, String lastName) {
        firstName = "%" + firstName + "%";
        lastName = "%" + lastName + "%";
        return filmRepository.findByName(firstName, lastName);
    }

    @Override
    public Iterable<FilmEntity> findByLanguage(String language) {
        return filmRepository.findByLanguage(language);
    }

    @Override
    public Optional<FilmEntity> findById(Integer id) {
        return filmRepository.findById(id);
    }

    @Override
    public Iterable<FilmEntity> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public int save(FilmEntity filmEntity) {
        boolean found = false;
        Iterable<FilmEntity> AllFilms = findAll();
        for (FilmEntity existingFilms : AllFilms) {
            if (existingFilms.equals(filmEntity)) {
                found = true;
                break;
            }
        }
        if (!found) {
            return filmRepository.save(filmEntity).getFilmId();
        }
        return -1;
    }
}
