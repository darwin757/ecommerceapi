package com.darwin.ecommerceapi.dto.mapper;

import com.darwin.ecommerceapi.dto.CustomerDTO;
import com.darwin.ecommerceapi.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
