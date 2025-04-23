package com.example.service.impl;

import com.example.exception.ProductNotFoundException;
import com.example.model.dto.ProductDto;
import com.example.model.entity.Product;
import com.example.model.mapper.ProductMapper;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceimpl implements ProductService {
    private static final String PRODUCT_CACHE = "product";
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceimpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @CachePut(value = PRODUCT_CACHE , key = "#result.getId()")
    public ProductDto createProduct(ProductDto productDto) {
        Product entity = productMapper.toEntity(productDto);
        productRepository.save(entity);
        return productMapper.toDto(entity);
    }

    @Cacheable(value = PRODUCT_CACHE, key ="#id")
    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("product not doun " + id));

        return productMapper.toDto(product);
    }


    @Cacheable(value = PRODUCT_CACHE, key ="'all'")
    public List<ProductDto> getAll() {
        List<Product> all = productRepository.findAll();
        return productMapper.toDtoList(all);
    }

    @Cacheable(value = PRODUCT_CACHE, key ="#result.getId()")
    public String updateProduct (ProductDto productDto) {
        Long id = productDto.getId();

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("product not exist " + id));


        productMapper.mapDtoToEntity(productDto, product);
        productMapper.toDto(productRepository.save(product));


        return "product updated with id: " + id;
    }


    @CacheEvict(value = PRODUCT_CACHE, key = "#id")
    public String deleteProduct (Long id) {
        productRepository.deleteById(id);
        return "product deleted with id: " + id;
    }
}
