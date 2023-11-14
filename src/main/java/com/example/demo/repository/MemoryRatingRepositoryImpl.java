package com.example.demo.repository;
import com.example.demo.Model.Rating;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@Repository
public class MemoryRatingRepositoryImpl implements RatingRepository {
    private final Map<Integer, Rating> ratings = new ConcurrentHashMap<>();

    @Override
    public Rating save(Rating rating) {
        int id = getNextId();
        rating.setId(id);
        ratings.put(id, rating);
        return rating;
    }

    @Override
    public List<Rating> findByRecordId(int id) {
        return ratings.values()
                .stream()
                .filter(rating -> rating.getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(int id) {
        return ratings.remove(id) != null;
    }
    public int getNextId() {
        int maxId = ratings.keySet().stream()
                .max(Integer::compareTo)
                .orElse(0);

        return maxId + 1;
    }

}