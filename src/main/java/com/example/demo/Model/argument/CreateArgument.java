package com.example.demo.Model.argument;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateArgument {
    String name;
    String description;
    String link;
}
