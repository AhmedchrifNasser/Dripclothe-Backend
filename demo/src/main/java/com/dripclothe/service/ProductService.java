package com.dripclothe.service;

import com.dripclothe.model.*;
import com.dripclothe.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {
    private final ProductRepo productRepo;
    private final SizeRepo sizeRepo;
    private final ColorRepo colorRepo;
    private final StyleRepo styleRepo;
    private final PhotoRepo photoRepo;

    public ProductService(ProductRepo productRepo, SizeRepo sizeRepo, ColorRepo colorRepo, StyleRepo styleRepo, PhotoRepo photoRepo ) {
        this.productRepo = productRepo;
        this.sizeRepo = sizeRepo;
        this.colorRepo = colorRepo;
        this.styleRepo = styleRepo;
        this.photoRepo = photoRepo;
    }

    public List<Product> findAllProducts() {
        return productRepo.findAll();
    }

    public List<Product> findProductByStyleId(Integer styleId) {
        if (styleId == null) {
            //Throw an exception
            return null;
        }
        return productRepo.findAllByStyleId(styleId);
    }

    public Product findProductById(Integer productId) {
        if (productId == null) {
            //Throw an exception
            return null;
        }
        return productRepo.findById(productId).orElseThrow(
                () -> new EntityNotFoundException("Aucun artiste avec l'ID=" + productId + "n'ete trouve dans la BDD "));
    }

    public Product addProductWithStyle(Product product, Integer styleId) {
        Style style = styleRepo.findStyleById(styleId);
        product.setStyle(style);
        return productRepo.save(product);
    }
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }
    public void deleteProduct(Integer id){
        Product product = productRepo.findById(id).orElseThrow();
        productRepo.delete(product);
    }

    public Product updateProduct(Product product){
        Product test= productRepo.findById(product.getId()).orElseThrow();
        product.setStyle(test.getStyle());
        sizeRepo.saveAll(product.getSizes());
        colorRepo.saveAll(product.getColors());
        photoRepo.saveAll(product.getPhotos());
        return productRepo.save(product);
    }

    public List<Product> getProductsByCategoryName(String categoryName, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepo.findByCategoryName(categoryName, pageable);
        return productPage.getContent();
    }

    public List<Product> getProductsBySubCategoryName(String subCategoryName, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepo.findBySubCategoryName(subCategoryName, pageable);
        return productPage.getContent();
    }

    public List<Product> getProductsBySubCategoryName1(String subCategoryName1, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepo.findBySubCategory1Name(subCategoryName1, pageable);
        return productPage.getContent();
    }

    public List<Product> getProductsBySubCategoryName2(String subCategoryName2, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepo.findBySubCategory2Name(subCategoryName2, pageable);
        return productPage.getContent();
    }
}
