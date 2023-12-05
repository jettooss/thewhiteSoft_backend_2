package com.example.demo.RatingTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.example.demo.model.Rating;
import com.example.demo.repository.RatingRepository;
import com.example.demo.service.RatingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingServiceImpl ratingService;

    @Test
    void testGetRatingsByRecordId() {

        // Arrange
        int recordId = 1;
        List<Rating> expectedRatings = Arrays.asList(new Rating(1,2,"3dsada",2), new Rating(2,2,"3dsada",2));
        when(ratingRepository.findByRecordId(recordId)).thenReturn(expectedRatings);

        // Act
        List<Rating> actualRatings = ratingService.getRatingsByRecordId(recordId);

        // Assert
        assertEquals(expectedRatings, actualRatings);
    }

    @Test
    void testDelete() {

        // Arrange
        int ratingId = 1;

        // Act
        ratingService.delete(ratingId);

        // Assert
        verify(ratingRepository).deleteById(ratingId);
    }
}

