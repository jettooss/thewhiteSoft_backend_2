package com.example.demo.service.argument;
import lombok.*;


@Value
@Builder
@Getter
@Setter
@AllArgsConstructor
public class UpdateArgument {

    private String name;
    private String description;
    private String link;
}

