package com.example.demo.Model.argument;
import lombok.*;


@Value
@Builder
@Getter
@Setter
@AllArgsConstructor
public class CreateArgument {
    String name;
    String description;
    String link;
}
