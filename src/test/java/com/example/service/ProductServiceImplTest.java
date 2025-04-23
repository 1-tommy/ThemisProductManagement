package com.example.service;

import com.example.model.dto.ProductDto;
import com.example.model.entity.Product;
import com.example.model.mapper.ProductMapper;
import com.example.repository.ProductRepository;
import com.example.service.impl.ProductServiceimpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceimpl productServiceimpl;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;



    @Test
    void createProduct_shouldReturnProductDtoAndSaveProductEntity() {
        ProductDto inputDto = new ProductDto();
        inputDto.setId(1L);
        inputDto.setName("try to catch me tralala");


        Product savedEntity = new Product();
        savedEntity.setId(1L);
        savedEntity.setName("try to catch me tralala");


        ProductDto expectedDto = new ProductDto();
        expectedDto.setId(1L);
        expectedDto.setName("try to catch me tralala");


        Product productEntity = new Product();
        productEntity.setId(1L);
        productEntity.setName("try to catch me tralala");


        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        when(productRepository.save(productEntity)).thenReturn(productEntity);
        when(productMapper.toDto(productEntity)).thenReturn(expectedDto);
        when(productMapper.toEntity(inputDto)).thenReturn(productEntity);


        ProductDto actualDto = productServiceimpl.createProduct(inputDto);


        assertEquals(expectedDto.getId(), actualDto.getId());
        assertEquals(expectedDto.getName(), actualDto.getName());
        assertEquals(expectedDto.getPrice(),actualDto.getPrice());


        verify(productRepository).save(productEntity);
        verify(productMapper).toDto(savedEntity);
    }


}
