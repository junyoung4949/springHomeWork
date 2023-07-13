package com.example.blog.controller;

import com.example.blog.dto.UserRequestDto;
import com.example.blog.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원가입
    @PostMapping("/signup")
    public String signup(@RequestBody UserRequestDto requestDto) {
        userService.signup(requestDto);
        return "회원가입 성공!";
    }

    @GetMapping("/fail")
    public String failure() {
        return "로그인 하세요!";
    }
}
