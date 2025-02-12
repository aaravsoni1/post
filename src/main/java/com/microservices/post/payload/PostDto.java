package com.microservices.post.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private String postId;

    private String title;

    private String content;

    private String description;

    List<Comment> comments;
}
