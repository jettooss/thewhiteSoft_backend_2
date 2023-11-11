package com.example.demo.Api.controller.dto;
import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DtoData {
    int id;
    String name;
    String description;
    String link;
}