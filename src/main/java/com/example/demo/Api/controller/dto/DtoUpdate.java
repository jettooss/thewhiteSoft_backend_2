package com.example.demo.Api.controller.dto;
import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DtoUpdate {
    String name;
    String description;
    String link;
}
