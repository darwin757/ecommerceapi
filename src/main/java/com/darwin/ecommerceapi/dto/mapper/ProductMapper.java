package com.darwin.ecommerceapi.dto.mapper;

import com.darwin.ecommerceapi.dto.ProductDTO;
import com.darwin.ecommerceapi.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO productToProductDTO(Product product);
    Product productDTOToProduct(ProductDTO productDTO);
}
