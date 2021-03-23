package com.sparta.eng80.pressplay.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "store", schema = "sakila")
public class StoreEntity {
    private int storeId;
    private Timestamp lastUpdate;

    private StaffEntity manager;
    private AddressEntity address;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @Basic
    @Column(name = "last_update")
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_staff_id")
    public StaffEntity getManagerStaff() {
        return manager;
    }

    public void setManagerStaff(StaffEntity manager) {
        this.manager = manager;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreEntity that = (StoreEntity) o;
        return Objects.equals(storeId, that.storeId) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, lastUpdate);
    }
}
