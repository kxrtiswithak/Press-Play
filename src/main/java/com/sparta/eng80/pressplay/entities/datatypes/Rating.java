package com.sparta.eng80.pressplay.entities.datatypes;

public enum Rating {
    G("G"), PG("PG"), PG13("PG-13"), R("R"), NC17("NC-17");

    private String ratingCode;

    Rating(String ratingCode) {
        this.ratingCode = ratingCode;
    }

    public String getRatingCode() {
        return ratingCode;
    }

    public static Rating formRating(String ratingCode) {
        for (Rating rating :Rating.values()){
            if (rating.getRatingCode().equals(ratingCode)){
                return rating;
            }
        }
        throw new UnsupportedOperationException(
                "The rating " + ratingCode + " is not supported!");
    }
}
