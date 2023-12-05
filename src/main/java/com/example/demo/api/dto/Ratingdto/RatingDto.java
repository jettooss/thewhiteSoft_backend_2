package com.example.demo.api.dto.Ratingdto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class RatingDto {

    private  int id;

    private  int value;

    private   String comment;

    private  int recordId;

}
