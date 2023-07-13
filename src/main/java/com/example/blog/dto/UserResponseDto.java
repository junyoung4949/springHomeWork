package com.example.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private final String msg;

    public UserResponseDto(String msg) {
        this.msg = msg;
    }
}
