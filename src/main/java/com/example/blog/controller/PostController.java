package com.example.blog.controller;

import com.example.blog.dto.PostRequestDto;
import com.example.blog.dto.PostResponseDto;
import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.security.UserDetailsImpl;
import com.example.blog.service.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    // 서비스 주입받음
    PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //게시글 생성
    @PostMapping("/post")
    public PostResponseDto createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto requestDto) {
        User user = userDetails.getUser();
        return postService.createPost(requestDto, user);
    }

    //게시글 조회
    @GetMapping("/posts")
    public List<PostResponseDto> getPost() {
        return postService.getPost();
    }

    //게시글 하나조회
    @GetMapping("/post/{id}")
    public PostResponseDto getOnePost(@PathVariable Long id) {
        return postService.getOnePost(id);
    }

    //업데이트
    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto requestDto, @PathVariable Long id) {
        User user = userDetails.getUser();
        return postService.updatePost(requestDto, id, user);
    }

    //Delete
    @DeleteMapping("/post/{id}")
    public void deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto requestDto, @PathVariable Long id) {
        User user = userDetails.getUser();
        postService.deletePost(requestDto, id, user);
    }
}
