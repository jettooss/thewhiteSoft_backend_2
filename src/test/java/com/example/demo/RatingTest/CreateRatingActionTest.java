package com.example.demo.RatingTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.example.demo.action.CreateRatingAction;
import com.example.demo.action.argument.CreateRatingActionArgument;
import com.example.demo.model.Rating;
import com.example.demo.model.Record;
import com.example.demo.service.RatingService;
import com.example.demo.service.RecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CreateRatingActionTest {

    @Mock
    private RecordService recordService;

    @Mock
    private RatingService ratingService;

    @InjectMocks
    private CreateRatingAction ratingAction;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateRating() {
        // Arrange
        CreateRatingActionArgument argument = CreateRatingActionArgument.builder()
                .recordId(1)
                .value(5)
                .comment("Test comment")
                .build();

        Record createRecord = mock(Record.class);
        when(createRecord.getId()).thenReturn(1);

        when(recordService.searchByID(1)).thenReturn(createRecord);

        Rating createdRating = mock(Rating.class);
        when(createdRating.getId()).thenReturn(1);

        when(ratingService.create(any(CreateRatingActionArgument.class))).thenReturn(createdRating);

        // Act
        Rating result = ratingAction.execute(argument);

        // Assert
        assertEquals(1, result.getId());
    }
}