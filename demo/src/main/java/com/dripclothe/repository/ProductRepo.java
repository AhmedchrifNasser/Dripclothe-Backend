package com.dripclothe.repository;

import com.dripclothe.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findAllByStyleId(Integer styleId);

    @Query("SELECT p FROM Product p WHERE p.categories.name = :categoryName")
    Page<Product> findByCategoryName(String categoryName, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.categories.subcategory = :subCategoryName OR p.categories.subcategory1 = :subCategoryName OR p.categories.subcategory2 = :subCategoryName")
    Page<Product> findBySubCategoryName(String subCategoryName, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.categories.subcategory1 = :subCategoryName1 ")
    Page<Product> findBySubCategory1Name(String subCategoryName1, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.categories.subcategory2 = :subCategoryName2 ")
    Page<Product> findBySubCategory2Name(String subCategoryName2, Pageable pageable);

}
