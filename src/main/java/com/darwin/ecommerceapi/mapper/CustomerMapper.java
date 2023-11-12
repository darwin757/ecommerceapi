package com.darwin.ecommerceapi.mapper;

import com.darwin.ecommerceapi.controller.request.CustomerRequest;
import com.darwin.ecommerceapi.dto.CustomerDTO;
import com.darwin.ecommerceapi.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
    CustomerDTO dtoToResponse(CustomerDTO customerDTO);
    CustomerDTO requestToDto(CustomerRequest customerRequest);
}
