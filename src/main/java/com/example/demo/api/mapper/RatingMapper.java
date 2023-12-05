package com.example.demo.api.mapper;
import com.example.demo.action.argument.CreateRatingActionArgument;
import com.example.demo.api.dto.Ratingdto.RatingCreateDto;
import com.example.demo.api.dto.Ratingdto.RatingDto;
import com.example.demo.model.Rating;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;


@Mapper(componentModel = SPRING)
public interface RatingMapper {

    CreateRatingActionArgument toCreateArgument(RatingCreateDto dto);

    RatingDto toRatingResponseDto(Rating dto);

}