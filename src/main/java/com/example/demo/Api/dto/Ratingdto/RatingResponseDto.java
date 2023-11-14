package com.example.demo.Api.dto.Ratingdto;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class RatingResponseDto {
    int id;


    int value;

    String comment;
}
