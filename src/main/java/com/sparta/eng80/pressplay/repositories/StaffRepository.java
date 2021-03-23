package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.StaffEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends CrudRepository<StaffEntity, Integer> {

    @Query("SELECT c FROM StaffEntity c WHERE c.email = ?1")
    Optional<StaffEntity> findByEmail(String email);

}
