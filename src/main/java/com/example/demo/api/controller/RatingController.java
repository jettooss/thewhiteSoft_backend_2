package com.example.demo.api.controller;
import com.example.demo.action.CreateRatingAction;
import com.example.demo.action.argument.CreateRatingActionArgument;
import com.example.demo.api.dto.Ratingdto.RatingCreateDto;
import com.example.demo.api.dto.Ratingdto.RatingDto;
import com.example.demo.api.mapper.RatingMapper;
import com.example.demo.model.Rating;
import com.example.demo.service.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;
    private final RatingMapper ratingMapper;
    private final CreateRatingAction ratingAction;

    @PostMapping("/create")
    @Operation(description = "Создать запись")
    public RatingDto create(@Valid @RequestBody RatingCreateDto dto) {
        CreateRatingActionArgument argument = ratingMapper.toCreateArgument(dto);
        Rating createdRating = ratingAction.execute(argument);
        return ratingMapper.toRatingResponseDto(createdRating);
    }

    @GetMapping("list-by-record")
    @Operation(description = "Получить оценки по ID записи")
    public List<RatingDto> getRatingsByRecordId(@RequestParam int recordId) {
        List<Rating> ratings = ratingService.getRatingsByRecordId(recordId);

        return ratings.stream()
                .map(ratingMapper::toRatingResponseDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("{id}/delete")
    @Operation(description = "Удалить оценку по ID")
    public void deleteRating(@PathVariable int id) {
        ratingService.delete(id);
    }
}