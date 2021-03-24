package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.RentalEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface RentalRepository extends CrudRepository<RentalEntity, Integer> {

    Iterable<RentalEntity> findRentalEntitiesByCustomer_CustomerId(int id);

    @Query("SELECT r FROM RentalEntity r WHERE r.customer.customerId =? 1 AND r.inventory.rented = true AND r.returnDate <= ?2")
    Iterable<RentalEntity> findRentalsByCustomerWhereReturnDateIsBefore(int id, Timestamp date);

    @Query("SELECT r FROM RentalEntity r WHERE r.inventory.rented = true AND r.returnDate <= ?1")
    Iterable<RentalEntity> findRentalsWhereReturnDateIsBefore(Timestamp date);
}
