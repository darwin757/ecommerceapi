package com.darwin.ecommerceapi.mapper;

import com.darwin.ecommerceapi.controller.request.ProductRequest;
import com.darwin.ecommerceapi.controller.response.ProductResponse;
import com.darwin.ecommerceapi.dto.ProductDTO;
import com.darwin.ecommerceapi.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO productToProductDTO(Product product);
    Product productDTOToProduct(ProductDTO productDTO);
    ProductResponse dtoToResponse(ProductDTO productDTO);
    ProductDTO requestToDto(ProductRequest productRequest);
}
