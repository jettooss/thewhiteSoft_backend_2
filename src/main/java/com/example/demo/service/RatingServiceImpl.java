package com.example.demo.service;
import com.example.demo.Model.Rating;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.RatingRepository;
import com.example.demo.service.argument.Rating.RatingCreateArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    @Override
    public Rating addRating(RatingCreateArgument argument) {
        Rating rating = Rating.builder().value(argument.getValue()).comment(argument.getComment()).build();
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatingsByRecordId(int id) {
        List<Rating> ratings = ratingRepository.findByRecordId(id);
        if (ratings.isEmpty()) {
            throw new NotFoundException("Не найдена запись: " + id);
        }
        return ratings;
    }

    @Override
    public boolean deleteRating(int id) {
        return ratingRepository.deleteById(id);
    }
}