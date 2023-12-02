package com.example.demo.service;
import com.example.demo.Model.Rating;
import com.example.demo.service.argument.Rating.RatingCreateArgument;
import java.util.List;

public interface RatingService {

    List<Rating> getRatingsByRecordId(int id);
    List<Rating> getRatingsById(int id);
    void delete(int id);

}