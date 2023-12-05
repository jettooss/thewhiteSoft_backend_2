package com.example.demo.repository;
import com.example.demo.model.Rating;
import java.util.List;

public interface RatingRepository {
    Rating save(Rating rating);

    List<Rating> findByRecordId(int id);
    List<Rating> findById(int id);

    void deleteById(int id);
    public int  getNextId();

}