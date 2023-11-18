package com.example.demo.repository;
import com.example.demo.Model.Rating;
import java.util.List;

public interface RatingRepository {
    Rating save(Rating rating);

    List<Rating> findByRecordId(int id);

    boolean deleteById(int id);
}