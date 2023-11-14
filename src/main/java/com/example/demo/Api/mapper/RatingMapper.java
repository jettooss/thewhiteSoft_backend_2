package com.example.demo.Api.mapper;
import com.example.demo.Api.dto.Ratingdto.RatingCreateDto;
import com.example.demo.Api.dto.Ratingdto.RatingResponseDto;
import com.example.demo.Model.Rating;
import com.example.demo.service.argument.Rating.RatingCreateArgument;
import org.mapstruct.Mapper;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;


@Mapper(componentModel = SPRING)
public interface RatingMapper {

    RatingCreateArgument toCreateArgument(RatingCreateDto dto);

    RatingResponseDto toRatingResponseDto(Rating dto); // Add this method

}