package com.example.demo.service.argument;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateArgument {
    String name;
    String description;
    String link;
}

