package com.example.demo.repository;

import com.example.demo.Model.Rating;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RatingRepository {
    private final Map<Integer, Rating> ratings;

    public RatingRepository() {
        this.ratings = new HashMap<>();
    }

    public Rating save(Rating rating) {
        ratings.put(rating.getId(), rating);
        return rating;
    }

    public void deleteById(int id) {
        ratings.remove(id);
    }

    public Rating findByRecordId(Integer id) {
        return ratings.get(id);
    }

}