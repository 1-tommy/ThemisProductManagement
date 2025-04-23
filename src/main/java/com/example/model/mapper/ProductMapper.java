package com.example.model.mapper;


import com.example.model.dto.ProductDto;
import com.example.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity (ProductDto productDto) ;

    ProductDto toDto (Product product) ;

    List<ProductDto> toDtoList (List<Product> productDtoList) ;

    void mapDtoToEntity(ProductDto productDto, @MappingTarget Product product) ;

}
