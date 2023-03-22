package com.example.springblog.entity;

import com.example.springblog.enums.PostStatus;
import com.example.springblog.validation.BlogPostTitleAlreadyExists;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@BlogPostTitleAlreadyExists
public class Post {
    private Integer id;

    @NotNull
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters long")
    private String title;

    @NotNull
    @Size(min = 3, max = 500, message = "Description must be between 3 and 500 characters long")
    private String description;

    @NotNull
    @Size(min = 3, max = 5000, message = "Body must be between 3 and 5000 characters long")
    private String body;

    private String slug;

    private PostStatus postStatus;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private List<Comment> comments;
}
