package com.darwin.ecommerceapi.service;

import com.darwin.ecommerceapi.dto.OrderDTO;
import com.darwin.ecommerceapi.dto.mapper.OrderMapper;
import com.darwin.ecommerceapi.model.Order;
import com.darwin.ecommerceapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    public List<OrderDTO> findAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::orderToOrderDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO findOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::orderToOrderDTO)
                .orElse(null);
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Order order = orderMapper.orderDTOToOrder(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.orderToOrderDTO(savedOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}

