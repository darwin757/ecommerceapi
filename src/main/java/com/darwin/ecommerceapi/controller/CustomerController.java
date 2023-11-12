package com.darwin.ecommerceapi.controller;

import com.darwin.ecommerceapi.controller.request.CustomerRequest;
import com.darwin.ecommerceapi.controller.response.CustomerResponse;
import com.darwin.ecommerceapi.dto.CustomerDTO;
import com.darwin.ecommerceapi.mapper.CustomerMapper;
import com.darwin.ecommerceapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> responses = customerService.findAllCustomers().stream()
                .map(customerMapper::dtoToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id)
                .map(customerMapper::dtoToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest request) {
        CustomerDTO dto = customerMapper.requestToDto(request);
        CustomerDTO savedDto = customerService.saveCustomer(dto);
        CustomerResponse response = customerMapper.dtoToResponse(savedDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest request) {
        CustomerDTO dto = customerMapper.requestToDto(request);
        dto.setId(id);
        CustomerDTO updatedDto = customerService.saveCustomer(dto);
        CustomerResponse response = customerMapper.dtoToResponse(updatedDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}

