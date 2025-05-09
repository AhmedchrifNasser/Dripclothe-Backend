package com.dripclothe.controller;

import com.dripclothe.model.Color;
import com.dripclothe.model.Photo;
import com.dripclothe.model.Product;
import com.dripclothe.model.Size;
import com.dripclothe.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/backend/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProductPerStyleId/{styleId}")
    public ResponseEntity<Product> addProduct(@RequestBody Product product, @PathVariable(value = "styleId") Integer styleId) {
        Product newProduct = productService.addProductWithStyle(product, styleId);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }
    @PutMapping("update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        Product updatedProduct = productService.updateProduct(product);
        return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id){
        productService.deleteProduct(id);
        return new ResponseEntity<String>("Product is deleted successfully.!", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/byStyle/{styleId}")
    public ResponseEntity<List<Product>> getProductsByStyleId(@PathVariable(value = "styleId") Integer styleId) {
        List<Product> products = productService.findProductByStyleId(styleId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductsById(@PathVariable(value = "productId") Integer productId) {
        Product product = productService.findProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Product>> getProductsByCategory(
            @PathVariable String categoryName,
            @RequestParam(defaultValue = "0") int page,  // Default page 0
            @RequestParam(defaultValue = "10") int size) {  // Default size 10
        List<Product> productsPage = productService.getProductsByCategoryName(categoryName, page, size);
        return ResponseEntity.ok(productsPage);
    }
    @GetMapping("/subCategory/{subCategoryName}")
    public ResponseEntity<List<Product>> getProductsBySubCategory(
            @PathVariable(value = "subCategoryName") String subCategoryName,
            @RequestParam(defaultValue = "0") int page,  // Default page 0
            @RequestParam(defaultValue = "10") int size) {  // Default size 10
        List<Product> productsPage = productService.getProductsBySubCategoryName(subCategoryName, page, size);
        return ResponseEntity.ok(productsPage);
    }
    @GetMapping("/subCategory1/{subCategoryName1}")
    public ResponseEntity<List<Product>> getProductsBySubCategory1(
            @PathVariable(value = "subCategoryName1") String subCategoryName1,
            @RequestParam(defaultValue = "0") int page,  // Default page 0
            @RequestParam(defaultValue = "10") int size) {  // Default size 10
        List<Product> productsPage = productService.getProductsBySubCategoryName1(subCategoryName1, page, size);
        return ResponseEntity.ok(productsPage);
    }
    @GetMapping("/subCategory2/{subCategoryName2}")
    public ResponseEntity<List<Product>> getProductsBySubCategory2(
            @PathVariable(value = "subCategoryName2") String subCategoryName2,
            @RequestParam(defaultValue = "0") int page,  // Default page 0
            @RequestParam(defaultValue = "10") int size) {  // Default size 10
        List<Product> productsPage = productService.getProductsBySubCategoryName2(subCategoryName2, page, size);
        return ResponseEntity.ok(productsPage);
    }

}
