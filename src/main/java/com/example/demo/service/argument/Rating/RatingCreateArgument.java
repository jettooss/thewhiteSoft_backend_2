package com.example.demo.service.argument.Rating;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RatingCreateArgument {
    private int value;
    private String comment;
    private int recordId;  // Изменено с recordID на recordId

}