package com.example.demo.Api.controller;
import com.example.demo.Api.dto.Ratingdto.RatingCreateDto;
import com.example.demo.Api.dto.Ratingdto.RatingDto;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;
    private final RatingMapper ratingMapper;

    @PostMapping("/create")
    @Operation(description = "Создать запись")
    public RatingDto create(@Valid @RequestBody RatingCreateDto dto) {
        RatingCreateArgument argument = ratingMapper.toCreateArgument(dto);

        return ratingMapper.toRatingResponseDto(ratingService.addRating(argument));
    }

    @GetMapping("{id}/ist-by-record")
    @Operation(description = "Получить оценки по ID записи")
    public ResponseEntity<?> getRatingsByRecordId(@PathVariable("id") int id) {
        try {
            List<Rating> ratings = ratingService.getRatingsByRecordId(id);
            List<RatingDto> responseDtos = ratings.stream().map(ratingMapper::toRatingResponseDto).collect(Collectors.toList());
            return new ResponseEntity<>(responseDtos, HttpStatus.OK);
        } catch (NotFoundException ex) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", ex.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}/delete")
    @Operation(description = "Удалить оценку по ID")
    public ResponseEntity<Object> deleteRating(@PathVariable int id) {
        boolean deleted = ratingService.deleteRating(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {

            return new ResponseEntity<>("Не удалось удалить рейтинг с идентификатором ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}