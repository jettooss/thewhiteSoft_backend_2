package com.example.demo.api.dto.RecordDto;
import lombok.*;


@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RecordDto {
    private int id;
    private String name;
    private String description;
    private String link;

}
