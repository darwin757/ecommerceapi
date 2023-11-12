package com.darwin.ecommerceapi.mapper;

import com.darwin.ecommerceapi.controller.request.OrderRequest;
import com.darwin.ecommerceapi.controller.response.OrderResponse;
import com.darwin.ecommerceapi.dto.OrderDTO;
import com.darwin.ecommerceapi.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO orderToOrderDTO(Order order);
    Order orderDTOToOrder(OrderDTO orderDTO);
    OrderResponse dtoToResponse(OrderDTO orderDTo);
    OrderDTO requestToDto(OrderRequest orderRequest);
}

