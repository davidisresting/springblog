package com.example.springblog.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer id;

    @NotNull
    @Size(min = 3, max = 100, message = "Body must be between 3 and 100 characters long")
    private String body;

    @NotNull
    private String author;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
