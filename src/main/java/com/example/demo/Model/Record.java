package com.example.demo.Model;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private int id;
    private String name;

    @NotNull
    @Size(max = 64)
    private String description;

    @NotNull
    @Size(max = 64)
    private String link;
}