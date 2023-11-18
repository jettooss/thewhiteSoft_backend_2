package com.example.demo.service.argument;
import lombok.*;


@Value
@Builder
@Getter
@Setter
@AllArgsConstructor
public class UpdateArgument {
    String name;
    String description;
    String link;
}

