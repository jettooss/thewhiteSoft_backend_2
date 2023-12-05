package com.example.demo.action;
import com.example.demo.action.argument.CreateRatingActionArgument;
import com.example.demo.model.Rating;
import com.example.demo.model.Record;
import com.example.demo.service.RatingService;
import com.example.demo.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CreateRatingAction {

    private final RecordService recordService;
    private final RatingService ratingService;

    public Rating execute(CreateRatingActionArgument argument) {

        Record getExisting = recordService.searchByID(argument.getRecordId());

        CreateRatingActionArgument rating = CreateRatingActionArgument.builder()
                                                                      .value(argument.getValue())
                                                                      .comment(argument.getComment())
                                                                      .recordId(getExisting.getId())
                                                                      .build();

        return ratingService.create(rating);
    }
}
