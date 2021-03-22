package com.sparta.eng80.pressplay.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "city", schema = "sakila", catalog = "")
public class CityEntity {
    private int cityId;
    private String city;
    private Timestamp lastUpdate;

    @Id
    @Column(name = "city_id")
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
        CityEntity that = (CityEntity) o;
        return Objects.equals(cityId, that.cityId) && Objects.equals(city, that.city) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId, city, lastUpdate);
    }
}
