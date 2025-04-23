package com.example.service.impl;

import com.example.model.dto.ProductDto;
import com.example.model.dto.UserDto;
import com.example.model.entity.User;
import com.example.model.mapper.ProductMapper;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    public UserServiceImpl(UserRepository userRepository, ProductMapper productMapper) {
        this.userRepository = userRepository;
        this.productMapper = productMapper;
    }

    @Override
    public UserDto create(UserDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User(dto.getUsername(), dto.getEmail(), LocalDateTime.now());
        user = userRepository.save(user);

        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt());
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(u -> new UserDto(u.getId(), u.getUsername(), u.getEmail(), u.getCreatedAt()))
                .collect(toList());
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<ProductDto> products = user.getProducts().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());

        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                products
        );
    }

}
