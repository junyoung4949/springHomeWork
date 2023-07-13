package com.example.blog.dto;

import com.example.blog.entity.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String username;
    private String password;
    private UserRoleEnum role;
}
