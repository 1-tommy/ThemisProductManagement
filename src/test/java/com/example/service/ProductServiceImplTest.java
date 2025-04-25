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


    void getById_shouldReturnProductDtoAndSaveProductEntity() {
        Long id = 1L;

        Product product = new Product();
        product.setId(id);
        product.setName("i drive like 1Paul Walker girl ;)");

        ProductDto expectedDto = new ProductDto();
        expectedDto.setId(1L);
        expectedDto.setName("i drive like Paul Walker girl ;");

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(expectedDto);

        ProductDto actualDto = productServiceimpl.getById(id);
        assertEquals(expectedDto.getId(), actualDto.getId());
        assertEquals(expectedDto.getName(), actualDto.getName());

        verify(productRepository).findById(id);
        verify(productMapper).toDto(product);


    }

    @Test
    void getAll_shouldReturnListOfProductDtos() {

        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        List<Product> products = List.of(product);

        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Test Product");

        List<ProductDto> productDtos = List.of(productDto);

        when(productRepository.findAll()).thenReturn(products);
        when(productMapper.toDtoList(products)).thenReturn(productDtos);


        List<ProductDto> result = productServiceimpl.getAll();


        assertEquals(1, result.size());
        assertEquals("Test Product", result.get(0).getName());

        verify(productRepository).findAll();
        verify(productMapper).toDtoList(products);
    }
}
