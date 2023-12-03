package com.example.demo.Api.controller;
import com.example.demo.Api.dto.Ratingdto.RatingCreateDto;
import com.example.demo.Api.dto.Ratingdto.RatingDto;
import com.example.demo.Api.mapper.RatingMapper;
import com.example.demo.Action.RatingAction;
import com.example.demo.Model.Rating;
import com.example.demo.service.RatingService;
import com.example.demo.service.argument.Rating.RatingCreateArgument;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final RatingAction ratingAction;

    @PostMapping("/create")
    @Operation(description = "Создать запись")
    public RatingDto  create( @Valid @RequestBody RatingCreateDto dto) {

        RatingCreateArgument argument = ratingMapper.toCreateArgument(dto);

        Rating createdRating = ratingAction.create(argument);
        return ratingMapper.toRatingResponseDto(createdRating);
    }

    @GetMapping("list-by-record")
    @Operation(description = "Получить оценки по ID записи")
    public List<RatingDto> getRatingsByRecordId(
            @RequestParam int recordId,
            @RequestParam(required = false) Integer filterValue,
            @RequestParam(defaultValue = "value") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDirection,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));

        List<Rating> ratings = ratingService.getRatingsByRecordId(recordId, filterValue, pageable);

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