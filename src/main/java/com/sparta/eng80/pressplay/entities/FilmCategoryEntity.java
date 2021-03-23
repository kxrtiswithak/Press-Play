package com.sparta.eng80.pressplay.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "film_category", schema = "sakila")
@IdClass(FilmCategoryEntityPK.class)
public class FilmCategoryEntity {
    private Long id;
    private int filmId;
    private int categoryId;
    private Timestamp lastUpdate;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "film_id")
    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    @Id
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

        FilmCategoryEntity that = (FilmCategoryEntity) o;

        if (!Objects.equals(filmId, that.filmId)) return false;
        if (!Objects.equals(categoryId, that.categoryId)) return false;
        if (!Objects.equals(lastUpdate, that.lastUpdate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = filmId;
        result = 31 * result + (categoryId);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }
}
