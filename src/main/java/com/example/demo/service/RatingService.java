package com.example.demo.service;
import com.example.demo.Model.Rating;
import com.example.demo.service.argument.Rating.RatingCreateArgument;
import java.util.List;

public interface RatingService {
    Rating addRating(RatingCreateArgument record);

    List<Rating> getRatingsByRecordId(int id);

    boolean deleteRating(int id);
}