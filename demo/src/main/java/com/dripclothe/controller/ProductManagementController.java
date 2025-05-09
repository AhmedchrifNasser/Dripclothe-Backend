package com.dripclothe.controller;

import com.dripclothe.model.Product;
import com.dripclothe.model.ProductManagement;
import com.dripclothe.service.ProductManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/backend/productManagement")
public class ProductManagementController {

    private ProductManagementService productManagementService;

    public ProductManagementController(ProductManagementService productManagementService) {
        this.productManagementService = productManagementService;
    }

    @PostMapping("/addProductPerStyleId/{styleId}")
    public ResponseEntity<ProductManagement> addProductWithStyle(@RequestBody ProductManagement productManagement, @PathVariable(value = "styleId") Integer styleId) {
        ProductManagement newProduct = productManagementService.addProductManagementWithStyle(productManagement, styleId);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }
    @PostMapping("/addProduct")
    public ResponseEntity<ProductManagement> addProduct(@RequestBody ProductManagement productManagement) {
        ProductManagement newProduct = productManagementService.addProductManagement(productManagement);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductManagement>> getAllProducts() {
        List<ProductManagement> products = productManagementService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ProductManagement> updateProduct(@RequestBody ProductManagement productManagement) {
        ProductManagement UpdatedproductManagement = productManagementService.upodateProductManagement(productManagement);
        return new ResponseEntity<>(UpdatedproductManagement,HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductManagement> getProductManagementById(@PathVariable(value = "productId") Integer productId) {
        ProductManagement productManagement = productManagementService.findProductManagementById(productId);
        return new ResponseEntity<>(productManagement, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProductManagement(@PathVariable("id") Integer id){
        productManagementService.deleteProductManagement(id);
        return new ResponseEntity<String>("ProductManagement is deleted successfully.!", HttpStatus.OK);
    }

}
