package com.dripclothe.service;

import com.dripclothe.model.Categories;
import com.dripclothe.repository.CategoriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoriesRepo categoriesRepo;

    public Categories createCategories(Categories categories) {
        return categoriesRepo.save(categories);
    }

    public List<Categories> getAllCategories() {
        return categoriesRepo.findAll();
    }

    public List<Categories> getAllCategoriesByName(String categoryName) {
        return categoriesRepo.findByName(categoryName);
    }
}
