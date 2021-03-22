package com.sparta.eng80.pressplay.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "inventory", schema = "sakila", catalog = "")
public class InventoryEntity {
    private int inventoryId;
    private Timestamp lastUpdate;

    @Id
    @Column(name = "inventory_id")
    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
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
        InventoryEntity that = (InventoryEntity) o;
        return Objects.equals(inventoryId, that.inventoryId) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, lastUpdate);
    }
}
