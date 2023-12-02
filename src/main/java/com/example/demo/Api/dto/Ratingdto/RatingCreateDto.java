package com.example.demo.Api.dto.Ratingdto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class RatingCreateDto {

    @Max(value = 5, message = "Значение должно быть между 1 и 5")
    @Min(value = 1, message = "Значение должно быть больше 0")
    int value;

    @NotBlank(message = "Комментарий не может быть пустым")
    String comment;

    int recordId;
}
