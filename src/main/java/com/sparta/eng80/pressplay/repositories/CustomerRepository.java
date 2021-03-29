package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {

    Optional<CustomerEntity> findCustomerEntityByEmailEquals(String email);
}
