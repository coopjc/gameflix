package com.gameflix.model;

public enum GameRating {
    EVERYONE("Everyone"),
    EVERYONE_TEN_PLUS("Everyone 10+"),
    TEEN("Teen 13+"),
    MATURE("Mature 17+"),
    ADULTS_ONLY("Adults Only 18+"),
    RATING_PENDING("Rating Pending");

    private String label;

    GameRating(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}