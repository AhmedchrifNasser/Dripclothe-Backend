package com.dripclothe.repository;

import com.dripclothe.model.Product;
import com.dripclothe.model.ProductManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductManagementRepo extends JpaRepository<ProductManagement, Integer> {
    ProductManagement findByProduct(Product product);
    ProductManagement findByProduct_Id(Integer product_id);
    void deleteByProduct_Id(Integer product_id);
}
