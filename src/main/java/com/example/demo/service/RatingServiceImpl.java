package com.example.demo.service;
import com.example.demo.Model.Rating;
import com.example.demo.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Override
    public Optional<Rating> getRatingsById(int id) {
        return ratingRepository.findById(id);

    }

    @Override
    public List<Rating> getRatingsByRecordId(int id, Integer filterValue, Pageable pageable) {
        Specification<Rating> spec = Specification.where(recordIdEquals(id))
                .and(valueEquals(filterValue));

        return ratingRepository.findAll(spec, pageable).getContent();
    }

    @Override
    public void delete(int id) {
        ratingRepository.deleteById(id);
    }

    private Specification<Rating> recordIdEquals(int recordId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("record").get("id"), recordId);
    }

    private Specification<Rating> valueEquals(Integer filterValue) {
        return (root, query, criteriaBuilder) ->
                filterValue == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.equal(root.get("value"), filterValue);
    }
}