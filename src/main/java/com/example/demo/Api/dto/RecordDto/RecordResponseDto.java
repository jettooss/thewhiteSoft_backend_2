package com.example.demo.Api.dto.RecordDto;
import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RecordResponseDto  {
    int id;
    String name;
    String description;
    String link;

}
