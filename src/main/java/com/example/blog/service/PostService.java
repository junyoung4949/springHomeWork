package com.example.blog.service;

import com.example.blog.dto.PostRequestDto;
import com.example.blog.dto.PostResponseDto;
import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.entity.UserRoleEnum;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    // 레포지토리 주입받음
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // 게시글 생성
    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        Post post = new Post(requestDto, user);
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    // 게시글 전체조회
    public List<PostResponseDto> getPost() {
        List<PostResponseDto> responseDtoList = new ArrayList<>();
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();

        for (Post a: postList) {
            responseDtoList.add(new PostResponseDto(a));
        }

        return responseDtoList;
    }

    // 게시글 하나 조회
    public PostResponseDto getOnePost(Long id) {
        Post post = findById(id);
        return new PostResponseDto(post);
    }


    //게시글 업데이트
    @Transactional
    public PostResponseDto updatePost(PostRequestDto requestDto, Long id, User user) {
        Post post = findById(id);

        if (post.getUsername().equals(user.getUsername()) || user.getRole().equals(UserRoleEnum.ADMIN)){
            post.update(requestDto);
        } else {
            throw new IllegalArgumentException("본인이 아니거나,관리자가 아니면 불가능.");
        }

        return new PostResponseDto(post);
    }


    // 게시글 지우기
    public void deletePost(PostRequestDto requestDto, Long id, User user) {
        Post post = findById(id);

        if (post.getUsername().equals(user.getUsername()) || user.getRole().equals(UserRoleEnum.ADMIN)) {
            postRepository.delete(post);
        } else {
            throw new IllegalArgumentException("본인이 아니거나,관리자가 아니면 불가능.");
        }
    }


    // 아이디로 PostEntity찾기
    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 없습니다.")
        );
    }
}
