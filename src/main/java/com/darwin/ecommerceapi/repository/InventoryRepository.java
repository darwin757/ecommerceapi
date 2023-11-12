package com.darwin.ecommerceapi.repository;
import com.darwin.ecommerceapi.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    // Custom query methods can be added here if needed
}

