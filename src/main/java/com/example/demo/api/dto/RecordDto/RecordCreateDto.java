package com.example.demo.api.dto.RecordDto;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordCreateDto {
    private String name;
    private String description;
    private String link;
}