package com.microservices.post.service;

import com.microservices.post.config.RestTemplateConfig;
import com.microservices.post.entity.Post;
import com.microservices.post.payload.PostDto;
import com.microservices.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RestTemplateConfig restTemplateConfig;

    public Post savePost(Post post) {
        UUID postId = UUID.fromString(UUID.randomUUID().toString());
        post.setId(String.valueOf(postId));
        Post savedPost =postRepository.save(post);
        return  savedPost;
    }

    public Post findPostById(String postId) {
       Post post= postRepository.findById(postId).get();
        return post;
    }

    public PostDto getPostWithComments(String postId) {
        Post post = postRepository.findById(postId).get();
        ArrayList comments = restTemplateConfig.getRestTemplate().getForObject("http://COMMENT-SERVICE/api/v1/comments/" + postId, ArrayList.class);
       PostDto postDto = new PostDto();
       postDto.setPostId(postId);
       postDto.setTitle(post.getTitle());
       postDto.setDescription(post.getDescription());
       postDto.setContent(post.getContent());
       postDto.setComments(comments);
       return postDto;
    }

}