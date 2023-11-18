package com.example.demo.service.argument.Rating;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RatingCreateArgument {
      int value;
      String comment;
}