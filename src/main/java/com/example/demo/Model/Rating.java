package com.example.demo.Model;
import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    private int id;

    @Min(value = 1, message = "Оценка должна быть не меньше 1")
    @Max(value = 5, message = "Оценка должна быть не больше 5")
    private int value;

    @NotBlank(message = "Комментарий не может быть пустым")
    private String comment;
}