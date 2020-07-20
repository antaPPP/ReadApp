package com.readapp.backend.dto;

import com.readapp.backend.models.Rate;

public class RateResponse {

    private String id;

    private Double score;

    private Double currentScore;

    public RateResponse(){}

    public RateResponse(Rate rate) {
        this.id = String.valueOf(rate.getId());
        this.score = rate.getScore();
    }

    public Double getCurrentScore() {
        return currentScore;
    }

    public RateResponse setCurrentScore(Double currentScore) {
        this.currentScore = currentScore;
        return this;
    }

    public String getId() {
        return id;
    }

    public RateResponse setId(String id) {
        this.id = id;
        return this;
    }

    public Double getScore() {
        return score;
    }

    public RateResponse setScore(Double score) {
        this.score = score;
        return this;
    }
}
