package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.CustomerEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {

    @Query("SELECT c FROM CustomerEntity c WHERE c.email = ?1")
    Optional<CustomerEntity> findByEmail(String email);
}
