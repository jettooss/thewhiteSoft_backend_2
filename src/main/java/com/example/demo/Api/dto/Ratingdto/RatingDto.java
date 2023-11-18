package com.example.demo.Api.dto.Ratingdto;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class RatingDto {

    int id;

    int value;

    String comment;
}
