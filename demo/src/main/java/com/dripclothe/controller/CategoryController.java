package com.dripclothe.controller;

import com.dripclothe.model.Categories;
import com.dripclothe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/backend/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("add")
    public Categories createCategories(@RequestBody Categories categories) {
        return categoryService.createCategories(categories);
    }

    @GetMapping("/all")
    public List<Categories> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/all/{categoryName}")
    public List<Categories> getAllCategories(@PathVariable(value = "categoryName") String categoryName) {
        return categoryService.getAllCategoriesByName(categoryName);
    }
}
