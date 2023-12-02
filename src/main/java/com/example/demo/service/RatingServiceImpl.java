package com.example.demo.service;
import com.example.demo.Model.Rating;
import com.example.demo.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

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