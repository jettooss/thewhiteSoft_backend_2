package com.example.demo.model;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    private int id;

    private int value;

    private String comment;

    private int recordId;

}