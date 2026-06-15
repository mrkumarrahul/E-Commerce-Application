package com.ecommerce.project.controller;


import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {


    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    private CategoryServiceImpl categoryService;

    @GetMapping("api/public/categories")
    public List<Category> getAllcategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/api/public/categories")
    public String createcategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return "category Added successfully";
    }

}
