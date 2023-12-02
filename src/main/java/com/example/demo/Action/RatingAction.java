package com.example.demo.Action;
import com.example.demo.Model.Record;
import com.example.demo.Model.Rating;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.RatingRepository;
import com.example.demo.repository.RecordRepository;
import com.example.demo.service.argument.Rating.RatingCreateArgument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RatingAction {

    private final RatingRepository ratingRepository;
    private final RecordRepository recordRepository;

    @Autowired
    public RatingAction(RatingRepository ratingRepository, RecordRepository recordRepository) {
        this.ratingRepository = ratingRepository;
        this.recordRepository = recordRepository;
    }

    public Rating create(RatingCreateArgument b) {

        Record existingRecord = recordRepository.getRecordById(b.getRecordId());

        if (existingRecord == null) {
            throw new NotFoundException("Запись с ID " + b.getRecordId() + " не найдена");
        }

        Rating rating = Rating.builder()
                .value(b.getValue())
                .comment(b.getComment())
                .recordId(existingRecord.getId())
                .build();

        return ratingRepository.save(rating);
    }
}
