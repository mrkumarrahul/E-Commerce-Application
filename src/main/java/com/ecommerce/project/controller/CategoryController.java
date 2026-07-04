package com.ecommerce.project.controller;


import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {


    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    private CategoryServiceImpl categoryService;

    @GetMapping("api/public/categories")
    public ResponseEntity<CategoryResponse> getAllcategories(){
        CategoryResponse categoryResponse= categoryService.getAllCategories();
        return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
    }


    @PostMapping("/api/public/categories")
    public ResponseEntity<String> createcategory(@Valid @RequestBody Category category){
       categoryService.createCategory(category);
        return new ResponseEntity<>("Category added successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable long categoryId){
            String status=categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status,HttpStatus.OK);
    }

    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody Category category,@PathVariable Long categoryId){
          Category savedCategory=categoryService.updateCategory(category,categoryId);
          return new ResponseEntity<>("Category with category id: "+categoryId,HttpStatus.OK);
      }
    }


