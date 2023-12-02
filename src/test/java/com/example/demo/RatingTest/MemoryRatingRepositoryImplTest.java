package com.example.demo.RatingTest;

import com.example.demo.Model.Rating;
import com.example.demo.repository.MemoryRatingRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryRatingRepositoryImplTest {

    @Test
    void testSave() {
        // Arrange
        MemoryRatingRepositoryImpl ratingRepository = new MemoryRatingRepositoryImpl();
        Rating ratingToSave = Rating.builder().value(5).comment("Test comment").recordId(1).build();

        // Act
        Rating savedRating = ratingRepository.save(ratingToSave);

        // Assert
        assertNotNull(savedRating);
        assertNotEquals(0, savedRating.getId());
        assertEquals(ratingToSave.getValue(), savedRating.getValue());
        assertEquals(ratingToSave.getComment(), savedRating.getComment());
        assertEquals(ratingToSave.getRecordId(), savedRating.getRecordId());
    }

    @Test
    void testDeleteById() {
        // Arrange
        MemoryRatingRepositoryImpl ratingRepository = new MemoryRatingRepositoryImpl();
        Rating ratingToDelete = Rating.builder().value(5).comment("Test comment").recordId(1).build();
        Rating savedRating = ratingRepository.save(ratingToDelete);

        // Act
        ratingRepository.deleteById(savedRating.getId());

        // Assert
        assertTrue(ratingRepository.findByRecordId(1).isEmpty());
    }

    @Test
    void testGetNextId() {
        // Arrange
        MemoryRatingRepositoryImpl ratingRepository = new MemoryRatingRepositoryImpl();
        Rating rating1 = Rating.builder().value(5).comment("Test comment 1").recordId(1).build();
        Rating rating2 = Rating.builder().value(4).comment("Test comment 2").recordId(1).build();

        ratingRepository.save(rating1);
        ratingRepository.save(rating2);

        // Act
        int nextId = ratingRepository.getNextId();

        // Assert
        assertEquals(3, nextId);
    }
}