package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {
}
