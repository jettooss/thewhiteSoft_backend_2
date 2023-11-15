package com.example.demo.Api.dto.RecordDto;
import lombok.*;


@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RecordResponseDto  {
    private int id;
    private String name;
    private String description;
    private String link;

}
