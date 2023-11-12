package com.darwin.ecommerceapi.service;

import com.darwin.ecommerceapi.dto.InventoryDTO;
import com.darwin.ecommerceapi.mapper.InventoryMapper;
import com.darwin.ecommerceapi.model.Inventory;
import com.darwin.ecommerceapi.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryMapper inventoryMapper;

    public List<InventoryDTO> findAllInventories() {
        return inventoryRepository.findAll().stream()
                .map(inventoryMapper::inventoryToInventoryDTO)
                .collect(Collectors.toList());
    }

    public Optional<InventoryDTO> findInventoryById(Long id) {
        return inventoryRepository.findById(id)
                .map(inventoryMapper::inventoryToInventoryDTO);
    }

    public InventoryDTO saveInventory(InventoryDTO inventoryDTO) {
        Inventory inventory = inventoryMapper.inventoryDTOToInventory(inventoryDTO);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return inventoryMapper.inventoryToInventoryDTO(savedInventory);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}


