package com.dripclothe.service;

import com.dripclothe.model.Product;
import com.dripclothe.model.ProductManagement;
import com.dripclothe.repository.ProductManagementRepo;
import com.dripclothe.repository.ProductRepo;
import com.dripclothe.repository.StyleRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductManagementService {

    private final ProductManagementRepo productManagementRepo;
    private final ProductService productService;

    public ProductManagementService(ProductManagementRepo productManagementRepo, ProductRepo productRepo, StyleRepo styleRepo, ProductService productService, ProductService productService1) {
        this.productManagementRepo = productManagementRepo;
        this.productService = productService1;
    }

    public List<ProductManagement> getAllProducts() {
        return productManagementRepo.findAll();
    }

    public ProductManagement getProductManagementByProduct(Product product) {
        return productManagementRepo.findByProduct(product);
    }

    public ProductManagement addProductManagementWithStyle(ProductManagement productManagement, Integer styleId) {
        Product savedProduct  = this.productService.addProductWithStyle(productManagement.getProduct(), styleId);
        productManagement.setProduct(savedProduct);
        return productManagementRepo.save(productManagement);
    }

    public ProductManagement addProductManagement(ProductManagement productManagement) {
        Product savedProduct  = this.productService.addProduct(productManagement.getProduct());
        productManagement.setProduct(savedProduct);
        return productManagementRepo.save(productManagement);
    }

    public ProductManagement upodateProductManagement(ProductManagement productManagement) {
        this.productService.updateProduct(productManagement.getProduct());
        return productManagementRepo.save(productManagement);
    }

    public ProductManagement findProductManagementById(Integer productId) {
        Product product;
        product = this.productService.findProductById(productId);
        if (product == null) {
            return null;
        }
        return productManagementRepo.findByProduct(product);
    }

    @Transactional
    public void deleteProductManagement(Integer id){
        productManagementRepo.deleteByProduct_Id(id);
    }
}
