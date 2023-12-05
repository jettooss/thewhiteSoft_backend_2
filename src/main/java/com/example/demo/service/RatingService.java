package com.example.demo.service;
import com.example.demo.action.argument.CreateRatingActionArgument;
import com.example.demo.model.Rating;
import java.util.List;

public interface RatingService {

    List<Rating> getRatingsByRecordId(int id);
    List<Rating> getRatingsById(int id);
    void delete(int id);
    Rating create(CreateRatingActionArgument argument);

}