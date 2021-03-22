package com.sparta.eng80.pressplay.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "rental", schema = "sakila", catalog = "")
public class RentalEntity {
    private int rentalId;
    private Timestamp rentalDate;
    private Timestamp returnDate;
    private Timestamp lastUpdate;

    @Id
    @Column(name = "rental_id")
    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    @Basic
    @Column(name = "rental_date")
    public Timestamp getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Timestamp rentalDate) {
        this.rentalDate = rentalDate;
    }

    @Basic
    @Column(name = "return_date")
    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    @Basic
    @Column(name = "last_update")
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalEntity that = (RentalEntity) o;
        return rentalId == that.rentalId && Objects.equals(rentalDate, that.rentalDate) && Objects.equals(returnDate, that.returnDate) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentalId, rentalDate, returnDate, lastUpdate);
    }
}
