package com.darwin.ecommerceapi.dto.mapper;

import com.darwin.ecommerceapi.dto.OrderDTO;
import com.darwin.ecommerceapi.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO orderToOrderDTO(Order order);
    Order orderDTOToOrder(OrderDTO orderDTO);
}

