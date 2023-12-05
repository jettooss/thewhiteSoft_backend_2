package com.example.demo.action.argument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class CreateRatingActionArgument {

    private int value;
    private String comment;
    private int recordId;
}
