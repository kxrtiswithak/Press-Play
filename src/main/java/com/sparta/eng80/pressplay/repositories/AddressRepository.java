package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.AddressEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {

    @Query("SELECT a FROM CustomerEntity c JOIN AddressEntity a ON c.address = a JOIN CityEntity ci ON a.city = ci JOIN CountryEntity co ON ci.country = co WHERE c.customerId = ?1")
    Optional<AddressEntity> findFullAddressByCustomerId(int id);
}
