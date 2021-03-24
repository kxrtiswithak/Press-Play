package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.RentalEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface RentalRepository extends CrudRepository<RentalEntity, Integer> {

    Iterable<RentalEntity> findRentalEntitiesByCustomer_CustomerId(int id);

    @Query(value = "SELECT * FROM rental r JOIN inventory i ON r.iventory_id == i.inventory_id WHERE r.customer.customerId =? 1 AND r.inventory.rented = true AND r.returnDate <= ?2 AND (SELECT count(*) FROM rental r2 WHERE r2.inventory_id = r.inventory_id AND r2_return_date >= r.return_date) <= 1", nativeQuery = true)
    Iterable<RentalEntity> findMostRecentRentalsForCustomerGroupedByInventoryIdWhereReturnDateIsBefore(int id, Timestamp date);

    @Query(value = "SELECT * FROM rental r JOIN inventory i ON r.iventory_id == i.inventory_id WHERE i.is_rented = true AND r.return_date <= ?1 AND (SELECT count(*) FROM rental r2 WHERE r2.inventory_id = r.inventory_id AND r2_return_date >= r.return_date) <= 1", nativeQuery = true)
    Iterable<RentalEntity> findMostRecentRentalsGroupedByInventoryIdWhereReturnDateIsBefore(Timestamp date);

    @Query(nativeQuery = true, value = "SELECT * FROM rental r JOIN inventory i ON r.iventory_id == i.inventory_id WHERE r.customer_id = ?1 and i.is_rented = 1")
    Iterable<RentalEntity> findRentedRentalEntityByCustomerID(int customerId);

    @Query(nativeQuery = true, value = "SELECT * FROM rental r WHERE r.inventory_id = ?1 ORDER BY r.rental_date LIMIT 1")
    RentalEntity findLatestRentalEntityByInventoryID(int inventoryId);
}
