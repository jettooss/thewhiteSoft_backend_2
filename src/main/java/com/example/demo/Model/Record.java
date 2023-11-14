package com.example.demo.Model;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private int id;
    private String name;

    private String description;

    private String link;
}