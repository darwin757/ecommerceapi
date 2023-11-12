package com.darwin.ecommerceapi.mapper;

import com.darwin.ecommerceapi.controller.request.InventoryRequest;
import com.darwin.ecommerceapi.controller.response.InventoryResponse;
import com.darwin.ecommerceapi.dto.InventoryDTO;
import com.darwin.ecommerceapi.model.Inventory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    InventoryDTO inventoryToInventoryDTO(Inventory inventory);
    Inventory inventoryDTOToInventory(InventoryDTO inventoryDTO);
    InventoryResponse dtoToResponse(InventoryDTO inventoryDTO);
    InventoryDTO requestToDto(InventoryRequest inventoryRequest);
}

