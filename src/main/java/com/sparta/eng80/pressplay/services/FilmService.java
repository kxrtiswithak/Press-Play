package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.CategoryEntity;
import com.sparta.eng80.pressplay.entities.FilmCategoryEntity;
import com.sparta.eng80.pressplay.entities.FilmEntity;
import com.sparta.eng80.pressplay.repositories.CategoryRepository;
import com.sparta.eng80.pressplay.repositories.FilmCategoryRepository;
import com.sparta.eng80.pressplay.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Iterable<FilmEntity> findByName(String filmName) {
        return null;
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
    public Iterable<FilmEntity> findByActor(String actorName) {
        return null;
    }

    @Override
    public Iterable<FilmEntity> findByLanguage(String language) {
        return null;
    }

    @Override
    public Optional<FilmEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Iterable<FilmEntity> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public void save(FilmEntity filmEntity) {

    }
}
