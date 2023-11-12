package com.example.demo.Api.controller;
import com.example.demo.Api.dto.DtoRating;
import com.example.demo.Api.service.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<DtoRating> createRating(@RequestBody DtoRating dtoRating) {
        DtoRating createdDto = ratingService.createRating(dtoRating);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @Operation(description = "Удаление оценки")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable int id) {
        ratingService.deleteRating(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @Operation(description = "Получение оценки по идентификатору записи")
    @GetMapping("/record/{recordId}")
    public ResponseEntity<DtoRating> getRatingByRecordId(@PathVariable int recordId) {
        DtoRating rating = ratingService.getRatingByRecordId(recordId);
        if (rating != null) {
            return new ResponseEntity<>(rating, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}