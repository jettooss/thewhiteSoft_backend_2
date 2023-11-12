package com.example.demo.Api.controller;

import com.example.demo.Api.dto.DtoRating;
import com.example.demo.Api.service.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "api/ratings")
public class RatingController {
    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @Operation(description = "Добавление оценки")
    @PostMapping("/create")
    public ResponseEntity<Object> createRating(@Valid @RequestBody DtoRating dtoRating, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        if (dtoRating.getRating() < 1 || dtoRating.getRating() > 5) {
            return new ResponseEntity<>("Рейтинг должен быть от 1 до 5", HttpStatus.BAD_REQUEST);
        }

        DtoRating createdDto = ratingService.createRating(dtoRating);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }
    @Operation(description = "Удаление оценки")
    @DeleteMapping("{id}/delete/")
    public ResponseEntity<Object> deleteRating(@PathVariable @Min(value = 1, message = "ID должен быть больше 0") int id) {
        boolean deleted = ratingService.deleteRating(id);

        if (deleted) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Оценка успешно удалена"), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Оценка с указанным id не найдена", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "Получение оценки по идентификатору записи")
    @GetMapping("{Id}/record/")
    public ResponseEntity<Object> getRatingByRecordId(@PathVariable @Min(value = 1, message = "ID записи должен быть больше 0") int recordId) {
        DtoRating rating = ratingService.getRatingByRecordId(recordId);
        if (rating != null) {
            return new ResponseEntity<>(rating, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Оценка не найдена", HttpStatus.NOT_FOUND);
        }
    }
}