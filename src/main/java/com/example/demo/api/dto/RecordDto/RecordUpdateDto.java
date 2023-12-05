package com.example.demo.api.dto.RecordDto;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordUpdateDto {
    private String name;
    private String description;
    private String link;
}
