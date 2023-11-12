package com.darwin.ecommerceapi.controller;

import com.darwin.ecommerceapi.controller.request.ProductRequest;
import com.darwin.ecommerceapi.controller.response.ProductResponse;
import com.darwin.ecommerceapi.dto.ProductDTO;
import com.darwin.ecommerceapi.mapper.ProductMapper;
import com.darwin.ecommerceapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> responses = productService.findAllProducts().stream()
                .map(productMapper::dtoToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return productService.findProductById(id)
                .map(dto -> productMapper.dtoToResponse(dto))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        ProductDTO dto = productMapper.requestToDto(request);
        ProductDTO savedDto = productService.saveProduct(dto);
        ProductResponse response = productMapper.dtoToResponse(savedDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        ProductDTO dto = productMapper.requestToDto(request);
        dto.setId(id);
        ProductDTO updatedDto = productService.saveProduct(dto);
        ProductResponse response = productMapper.dtoToResponse(updatedDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}

