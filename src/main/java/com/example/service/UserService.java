package com.example.service;

import com.example.model.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto create(UserDto dto);
    List<UserDto> getAll();
    UserDto getById(Long id);
}
