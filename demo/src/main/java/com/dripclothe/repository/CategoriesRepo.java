package com.dripclothe.repository;

import com.dripclothe.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepo extends JpaRepository<Categories, Integer> {
    List<Categories> findByName(String name);
}
