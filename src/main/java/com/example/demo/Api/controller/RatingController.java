package com.example.demo.Api.controller;
import com.example.demo.Api.dto.Ratingdto.RatingCreateDto;
import com.example.demo.Api.dto.Ratingdto.RatingResponseDto;
import com.example.demo.Api.mapper.RatingMapper;
import com.example.demo.Model.Rating;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.RatingService;
import com.example.demo.service.argument.Rating.RatingCreateArgument;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;
    private final RatingMapper ratingMapper;

    @PostMapping("{id}/create")
    @Operation(description = "Создать запись")
    public RatingResponseDto create(@Valid @RequestBody RatingCreateDto dto) {
        RatingCreateArgument argument = ratingMapper.toCreateArgument(dto);
        System.out.println(argument);

        return ratingMapper.toRatingResponseDto(ratingService.addRating(argument));
    }

    @GetMapping("{id}/getByRecordId")
    @Operation(description = "Получить оценки по ID записи")
    public List<RatingResponseDto> getRatingsByRecordId(@PathVariable("id") int id) {
        List<Rating> ratings = ratingService.getRatingsByRecordId(id);
        return ratings.stream()
                .map(ratingMapper::toRatingResponseDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("delete/{id}")
    @Operation(description = "Удалить оценку по ID")
    public ResponseEntity<Void> deleteRating(@PathVariable int id) {
        boolean deleted = ratingService.deleteRating(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new NotFoundException("id не найдено: " + id);
        }
    }
}