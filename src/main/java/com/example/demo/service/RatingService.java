package com.example.demo.service;
import com.example.demo.Model.Rating;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface RatingService {

    List<Rating> getRatingsByRecordId(int id, Integer filterValue, Pageable pageable);

    Optional<Rating> getRatingsById(int id);
    void delete(int id);

}