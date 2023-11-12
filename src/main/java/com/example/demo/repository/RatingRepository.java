package com.example.demo.repository;
import com.example.demo.Model.Rating;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;


@Repository
public class RatingRepository {
    private final Map<Integer, Rating> ratings;

    public RatingRepository() {
        this.ratings = new HashMap<>();
    }

    public   Rating save(Rating rating) {
        int nextId = getNextId();
        rating.setId(nextId);
        ratings.put(nextId, rating);
        return rating;
    }

    public boolean deleteById(int id) {
        return ratings.remove(id) != null;
    }

    public Rating findByRecordId(Integer id) {
        return ratings.get(id);
    }
    private int getNextId() {
        // Ваша логика получения уникального id, например, увеличение счетчика
        // Здесь может быть использована библиотека для генерации уникальных идентификаторов
        // Например, что-то вроде AtomicLong для увеличения id
        // На уровне реального приложения, обычно, используются базы данных для автоматической генерации id
        // Здесь для примера просто увеличиваем счетчик на 1
        return ratings.size() + 1;
    }

}