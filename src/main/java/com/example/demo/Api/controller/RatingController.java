package com.example.demo.Api.controller;
import com.example.demo.Api.dto.Ratingdto.RatingCreateDto;
import com.example.demo.Api.dto.Ratingdto.RatingDto;
import com.example.demo.Api.mapper.RatingMapper;
import com.example.demo.Model.Rating;
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

    @PostMapping("/create")
    @Operation(description = "Создать запись")
    public RatingDto create(@Valid @RequestBody RatingCreateDto dto) {
        RatingCreateArgument argument = ratingMapper.toCreateArgument(dto);

        return ratingMapper.toRatingResponseDto(ratingService.addRating(argument));
    }

    @GetMapping("{id}/ist-by-record")
    @Operation(description = "Получить оценки по ID записи")
    public ResponseEntity<?> getRatingsByRecordId(@PathVariable("id") int id) {

        List<Rating> ratings = ratingService.getRatingsByRecordId(id);
        List<RatingDto> responseDtos = ratings.stream().map(ratingMapper::toRatingResponseDto).collect(Collectors.toList());
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);

    }

    @DeleteMapping("{id}/delete")
    @Operation(description = "Удалить оценку по ID")
    public void deleteRating(@PathVariable int id) {
        ratingService.deleteRating(id);

    }
}