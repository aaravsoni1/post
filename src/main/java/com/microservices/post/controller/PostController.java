package com.microservices.post.controller;


import com.microservices.post.entity.Post;
import com.microservices.post.payload.PostDto;
import com.microservices.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping("addpost")
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
        Post newPost = postService.savePost(post);
        return  new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping("{postId}")
    public Post getPostById(@PathVariable String postId) {
        Post post = postService.findPostById(postId);
        return post;
    }

    @GetMapping("{postId}/comments")
    public ResponseEntity<PostDto> getPostWithComments(@PathVariable String postId){
      PostDto postDto = postService.getPostWithComments(postId);
     return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
}
