package com.example.demo.Api.dto;
import lombok.*;


@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DtoRating {
    int id;
    int rating;
    String comment;
}