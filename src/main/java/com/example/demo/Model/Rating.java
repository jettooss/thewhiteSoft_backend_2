package com.example.demo.Model;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    int id;
    int recordId;
    int rating;
    String comment;
}