package com.example.myboard.post.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostViewResponse {
    private Long id;
    private Long boardId;
    private String userName;
    private String email;
    private String title;
    private String content;
    private LocalDateTime postedAt;
}