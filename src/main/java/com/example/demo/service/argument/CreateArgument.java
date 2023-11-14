package com.example.demo.service.argument;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class CreateArgument {
    String name;
    String description;
    String link;
}
