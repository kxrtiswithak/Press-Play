package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.RentalEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface RentalRepository extends CrudRepository<RentalEntity, Integer> {

    Iterable<RentalEntity> findRentalEntitiesByCustomer_CustomerIdEqualsOrderByRentalDateDesc(int id);

    @Query(value = "SELECT * FROM (SELECT *, @rn \\:= IF(@prev = inventory_id, @rn + 1, 1) AS rn, @prev \\:= inventory_id FROM rental WHERE customer_id = ?1 ORDER BY inventory_id, return_date DESC) AS r JOIN inventory i ON r.inventory_id = i.inventory_id WHERE i.is_rented = true AND r.rn <= 1 AND r.return_date <= ?2", nativeQuery = true)
    Iterable<RentalEntity> findMostRecentRentalsForCustomerGroupedByInventoryIdWhereReturnDateIsBefore(int id, Timestamp date);

    @Query(value = "SELECT * FROM (SELECT *, @rn \\:= IF(@prev = inventory_id, @rn + 1, 1) AS rn, @prev \\:= inventory_id FROM rental ORDER BY inventory_id, return_date DESC) AS r JOIN inventory i ON r.inventory_id = i.inventory_id WHERE i.is_rented = true AND r.rn <= 1 AND r.return_date <= 1?;", nativeQuery = true)
    Iterable<RentalEntity> findMostRecentRentalsGroupedByInventoryIdWhereReturnDateIsBefore(Timestamp date);

    @Query(nativeQuery = true, value = "SELECT * FROM rental r JOIN inventory i ON r.inventory_id = i.inventory_id WHERE r.customer_id = ?1 and i.is_rented = 1")
    Iterable<RentalEntity> findCurrentRentalsCustomerID(int customerId);

    @Query(nativeQuery = true, value = "SELECT * FROM rental r WHERE r.inventory_id = ?1 ORDER BY r.rental_date LIMIT 1")
    RentalEntity findLatestRentalEntityByInventoryID(int inventoryId);
}
