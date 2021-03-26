package com.sparta.eng80.pressplay.entities;

import com.sparta.eng80.pressplay.entities.datatypes.Rating;
import com.sparta.eng80.pressplay.util.TitleCase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "film", schema = "sakila")
public class FilmEntity {
    private int filmId;
    private String title;
    private String description;
    private int releaseYear;
    private int rentalDuration;
    private BigDecimal rentalRate;
    private int length;
    private BigDecimal replacementCost;
    private Rating rating;
    private String specialFeatures;
    private Date lastUpdate;

    private LanguageEntity language;
    private LanguageEntity originalLanguage;

    private Set<ActorEntity> actors;
    private Set<CategoryEntity> categories;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return TitleCase.toTitleCase(title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "release_year")
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Basic
    @Column(name = "rental_duration")
    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    @Basic
    @Column(name = "rental_rate")
    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    @Basic
    @Column(name = "length")
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Basic
    @Column(name = "replacement_cost")
    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "rating")
    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "special_features")
    public String getSpecialFeatures() {
        return specialFeatures.replaceAll("[,]", "$0 ");
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    @Basic
    @Column(name = "last_update")
    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id")
    public LanguageEntity getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEntity language) {
        this.language = language;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "original_language_id")
    public LanguageEntity getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(LanguageEntity originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "film_actor",
        joinColumns = {
            @JoinColumn(name = "film_id")
        },
        inverseJoinColumns = {
                @JoinColumn(name = "actor_id")
        })
    public Set<ActorEntity> getActors() {
        return actors;
    }

    public void setActors(Set<ActorEntity> actors) {
        this.actors = actors;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "film_category",
            joinColumns = {
                    @JoinColumn(name = "film_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "category_id")
            })
    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmEntity that = (FilmEntity) o;
        return Objects.equals(filmId, that.filmId) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(releaseYear, that.releaseYear) && Objects.equals(rentalDuration, that.rentalDuration) && Objects.equals(rentalRate, that.rentalRate) && Objects.equals(length, that.length) && Objects.equals(replacementCost, that.replacementCost) && Objects.equals(rating, that.rating) && Objects.equals(specialFeatures, that.specialFeatures) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, title, description, releaseYear, rentalDuration, rentalRate, length, replacementCost, rating, specialFeatures, lastUpdate);
    }
}
