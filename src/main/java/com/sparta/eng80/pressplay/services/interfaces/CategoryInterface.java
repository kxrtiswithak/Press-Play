package com.sparta.eng80.pressplay.services.interfaces;

import com.sparta.eng80.pressplay.entities.CategoryEntity;

public interface CategoryInterface {
    void addCategory(String categoryName);
    void removeCategory(String categoryName);
}
