package com.example.demo.Api.dto;
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

    public DtoData(String name, String description, String link) {
        this.name = name;
        this.description = description;
        this.link = link;
    }
}
