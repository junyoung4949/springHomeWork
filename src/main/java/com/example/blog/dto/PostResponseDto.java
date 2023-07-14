package com.example.blog.dto;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
public class PostResponseDto {

    private Long id;
    private LocalDateTime creatAt;
    private LocalDateTime modifiedAt;
    private String title;
    private String username;
    private String content;

    private List<Comment> commentList;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getUsername();
        this.creatAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.commentList = post.getCommentList();
    }
}
