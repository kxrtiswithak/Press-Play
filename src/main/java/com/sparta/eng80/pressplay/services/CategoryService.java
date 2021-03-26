package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.CategoryEntity;
import com.sparta.eng80.pressplay.repositories.CategoryRepository;
import com.sparta.eng80.pressplay.services.interfaces.CategoryInterface;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements CategoryInterface {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addCategory(String category) {
        categoryRepository.addCategory(category);
    }

    @Override
    public void removeCategory(String category) {
        categoryRepository.removeCategory(category);
    }

    public CategoryEntity findCategory(String category) {
        return categoryRepository.findCategory(category);
    }
}
