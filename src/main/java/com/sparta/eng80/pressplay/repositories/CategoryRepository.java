package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.CategoryEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into category (name, last_update) values (?, CURDATE())")
    public void addCategory(String name);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from category where name = ?")
    public void removeCategory(String name);
}
