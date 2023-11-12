package com.darwin.ecommerceapi.controller;

import com.darwin.ecommerceapi.controller.request.OrderRequest;
import com.darwin.ecommerceapi.controller.response.OrderResponse;
import com.darwin.ecommerceapi.dto.OrderDTO;
import com.darwin.ecommerceapi.mapper.OrderMapper;
import com.darwin.ecommerceapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> responses = orderService.findAllOrders().stream()
                .map(orderMapper::dtoToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id)
                .map(orderMapper::dtoToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        OrderDTO dto = orderMapper.requestToDto(request);
        OrderDTO savedDto = orderService.saveOrder(dto);
        OrderResponse response = orderMapper.dtoToResponse(savedDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id, @RequestBody OrderRequest request) {
        OrderDTO dto = orderMapper.requestToDto(request);
        dto.setId(id);
        OrderDTO updatedDto = orderService.saveOrder(dto);
        OrderResponse response = orderMapper.dtoToResponse(updatedDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
}
