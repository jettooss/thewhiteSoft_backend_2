package com.example.demo.service;
import com.example.demo.Model.Rating;
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
        Rating rating = Rating.builder()
                .value(argument.getValue())
                .comment(argument.getComment())
                .build();
        return ratingRepository.save(rating);
    }

    @Override

    public List<Rating> getRatingsByRecordId(int id) {
        return ratingRepository.findByRecordId(id);
    }
    @Override
    public boolean deleteRating(int id) {
        return ratingRepository.deleteById(id);
    }
}