package com.example.demo.Api.service;
import com.example.demo.Api.dto.DtoRating;
import com.example.demo.Model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.RatingRepository;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public DtoRating createRating(DtoRating dtoRating) {
        Rating rating = Rating.builder()
                .rating(dtoRating.getRating())
                .comment(dtoRating.getComment())
                .build();

        Rating createdRating = ratingRepository.save(rating);

        return DtoRating.builder()
                .id(createdRating.getId())
                .rating(createdRating.getRating())
                .comment(createdRating.getComment())
                .build();
    }

    public boolean deleteRating(int ratingId) {

        return   ratingRepository.deleteById(ratingId);
    }

    public DtoRating getRatingByRecordId(int recordId) {
        Rating rating = ratingRepository.findByRecordId(recordId);

        if (rating != null) {
            return DtoRating.builder()
                    .id(rating.getId())
                    .rating(rating.getRating())
                    .comment(rating.getComment())
                    .build();
        } else {
            return null;
        }
    }
}