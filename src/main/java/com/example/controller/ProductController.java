package com.example.controller;

import com.example.model.dto.ProductDto;
import com.example.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/add")
    public ProductDto createProduct(ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/edit")
    public String productUpdate ( ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @GetMapping("/get-all")
    public List<ProductDto> getAll(){
        return productService.getAll();
    }

}
