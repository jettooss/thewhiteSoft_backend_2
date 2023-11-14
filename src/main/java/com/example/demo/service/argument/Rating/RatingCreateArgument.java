package com.example.demo.service.argument.Rating;

import lombok.*;

@Getter
@Setter
@Builder
//@NoArgsConstructor
@AllArgsConstructor
public class RatingCreateArgument {
     public int value;
     public String comment;
}