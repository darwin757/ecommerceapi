package com.darwin.ecommerceapi.controller;

import com.darwin.ecommerceapi.controller.request.InventoryRequest;
import com.darwin.ecommerceapi.controller.response.InventoryResponse;
import com.darwin.ecommerceapi.dto.InventoryDTO;
import com.darwin.ecommerceapi.mapper.InventoryMapper;
import com.darwin.ecommerceapi.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private InventoryMapper inventoryMapper;

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAllInventory() {
        List<InventoryResponse> responses = inventoryService.findAllInventories().stream()
                .map(inventoryMapper::dtoToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponse> getInventoryById(@PathVariable Long id) {
        return inventoryService.findInventoryById(id)
                .map(inventoryMapper::dtoToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InventoryResponse> createInventory(@RequestBody InventoryRequest request) {
        InventoryDTO dto = inventoryMapper.requestToDto(request);
        InventoryDTO savedDto = inventoryService.saveInventory(dto);
        InventoryResponse response = inventoryMapper.dtoToResponse(savedDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponse> updateInventory(@PathVariable Long id, @RequestBody InventoryRequest request) {
        InventoryDTO dto = inventoryMapper.requestToDto(request);
        dto.setId(id);
        InventoryDTO updatedDto = inventoryService.saveInventory(dto);
        InventoryResponse response = inventoryMapper.dtoToResponse(updatedDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok().build();
    }
}

