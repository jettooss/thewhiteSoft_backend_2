package com.example.demo.Model;
import lombok.*;


@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class Rating {

    private int id;

    private int value;

    private String comment;
}