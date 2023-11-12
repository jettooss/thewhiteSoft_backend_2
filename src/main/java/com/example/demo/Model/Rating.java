package com.example.demo.Model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    private static int idCounter = 1; // Добавьте счетчик id

    private int id;

    @Min(1)
    @Max(5)
    private int rating;

    @NotNull
    @Size(max = 64)
    private String comment;

    // Добавьте конструктор для установки уникального id
//    public Rating(int rating, String comment) {
//        this.id = idCounter++;
//        this.rating = rating;
//        this.comment = comment;
//    }
}