package com.example.demo.Api.service;
import com.example.demo.Api.dto.DtoRating;
import com.example.demo.Model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.RatingRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public DtoRating createRating(DtoRating dtoRating) {
        // Логика создания оценки
        Rating rating = Rating.builder()
                .recordId(dtoRating.getRecordId())
                .rating(dtoRating.getRating())
                .comment(dtoRating.getComment())
                .build();

        Rating createdRating = ratingRepository.save(rating);

        return DtoRating.builder()
                .id(createdRating.getId())
                .recordId(createdRating.getRecordId())
                .rating(createdRating.getRating())
                .comment(createdRating.getComment())
                .build();
    }

    public void deleteRating(int ratingId) {
        ratingRepository.deleteById(ratingId);
    }

    public DtoRating getRatingByRecordId(int recordId) {
        Rating rating = ratingRepository.findByRecordId(recordId);

        if (rating != null) {
            return DtoRating.builder()
                    .id(rating.getId())
                    .recordId(rating.getRecordId())
                    .rating(rating.getRating())
                    .comment(rating.getComment())
                    .build();
        } else {
            // Обработка случая, когда оценка не найдена
            // Возможно, бросить исключение или вернуть null/пустой DtoRating
            return null;
        }
    }

}
