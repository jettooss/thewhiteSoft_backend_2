package com.example.demo.RatingTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.example.demo.Action.RatingAction;
import com.example.demo.Model.Rating;
import com.example.demo.Model.Record;
import com.example.demo.repository.RatingRepository;
import com.example.demo.repository.RecordRepository;
import com.example.demo.service.argument.Rating.RatingCreateArgument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class RatingActionTest {

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private RecordRepository recordRepository;

    @InjectMocks
    private RatingAction ratingAction;

    @Test
    void testCreateRating() {

        // Arrange
        Record existingRecord = Record.builder().id(2).name("TestName").description("TestDescription").link("TestLink").build();
        RatingCreateArgument createArgument = RatingCreateArgument.builder().value(3).comment("4ddddddddddd").recordId(2).build();

        when(recordRepository.getRecordById(existingRecord.getId())).thenReturn(existingRecord);

        when(ratingRepository.save(any(Rating.class))).thenAnswer(invocation -> {
            Rating savedRating = invocation.getArgument(0);
            savedRating.setId(1);
            return savedRating;
        });

        // Act
        Rating createdRating = ratingAction.create(createArgument);

        // Assert
        assertNotNull(createdRating);
        assertEquals(existingRecord.getId(), createdRating.getRecordId());

    }
}


