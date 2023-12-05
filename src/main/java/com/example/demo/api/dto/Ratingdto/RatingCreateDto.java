package com.example.demo.api.dto.Ratingdto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class RatingCreateDto {

    @Max(value = 5, message = "Значение должно быть между 1 и 5")
    @Min(value = 1, message = "Значение должно быть больше 0")
    private int value;

    @NotBlank(message = "Комментарий не может быть пустым")
    private String comment;

    private int recordId;



}
