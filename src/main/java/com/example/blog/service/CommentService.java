package com.example.blog.service;

import com.example.blog.dto.CommentRequestDto;
import com.example.blog.dto.CommentResponseDto;
import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.entity.UserRoleEnum;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    CommentRepository commentRepository;
    PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    // comment 등록
    public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {
        Post post = postRepository.findById(requestDto.getPost_id()).orElseThrow(
                () -> new IllegalArgumentException("글이 존재하지 않습니다.")
        );
        Comment comment = new Comment(requestDto, post ,user);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }


    // comment 수정
    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그 댓글은 존재하지 않습니다.")
        );

        if (comment.getUsername().equals(user.getUsername()) || user.getRole().equals(UserRoleEnum.ADMIN)){
            comment.update(requestDto);
        } else {
            throw new IllegalArgumentException("본인이 아니거나,관리자가 아니면 불가능.");
        }

        return new CommentResponseDto(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그 댓글은 존재하지 않습니다.")
        );

        if (comment.getUsername().equals(user.getUsername()) || user.getRole().equals(UserRoleEnum.ADMIN)){
            commentRepository.delete(comment);
        } else {
            throw new IllegalArgumentException("본인이 아니거나,관리자가 아니면 불가능.");
        }
    }

}
