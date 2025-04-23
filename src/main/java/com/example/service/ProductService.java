package com.example.service;

import com.example.model.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);

    ProductDto getById(Long id);

    List<ProductDto> getAll();

    String updateProduct (ProductDto productDto);

    String deleteProduct(Long id);

}
