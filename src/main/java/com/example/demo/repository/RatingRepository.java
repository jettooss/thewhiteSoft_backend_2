package com.example.demo.repository;
import com.example.demo.Model.Rating;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;


@Repository
public class RatingRepository {
    private final Map<Integer, Rating> ratings;

    public RatingRepository() {
        this.ratings = new HashMap<>();
    }

    public   Rating save(Rating rating) {
        int nextId = getNextId();
        rating.setId(nextId);
        ratings.put(nextId, rating);
        return rating;
    }

    public boolean deleteById(int id) {
        return ratings.remove(id) != null;
    }

    public Rating findByRecordId(Integer id) {
        return ratings.get(id);
    }
    private int getNextId() {

        return ratings.size() + 1;
    }

}