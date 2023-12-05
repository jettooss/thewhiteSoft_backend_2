package com.example.demo.service;
import com.example.demo.action.argument.CreateRatingActionArgument;
import com.example.demo.model.Rating;
import com.example.demo.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Override
    public Rating create(CreateRatingActionArgument argument) {

        Rating rating = Rating.builder()
                .value(argument.getValue())
                .comment(argument.getComment())
                .recordId(argument.getRecordId())
                .build();
        return ratingRepository.save(rating);

    }

    @Override
    public List<Rating> getRatingsByRecordId(int id) {

        List<Rating> RecordId = ratingRepository.findByRecordId(id);

        return RecordId;
    }
    @Override
    public List<Rating> getRatingsById(int id) {

        List<Rating> IdRating = ratingRepository.findById(id);

        return IdRating;
    }
    @Override
    public void delete(int id) {

         ratingRepository.deleteById(id);
    }
}