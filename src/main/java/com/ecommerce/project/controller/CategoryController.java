package com.ecommerce.project.controller;


import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CategoryController {


    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    private CategoryServiceImpl categoryService;

    @GetMapping("api/public/categories")
    public ResponseEntity<List<Category>> getAllcategories(){
        List<Category> categories= categoryService.getAllCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }


    @PostMapping("/api/public/categories")
    public ResponseEntity<String> createcategory(@Valid @RequestBody Category category){
       categoryService.createCategory(category);
        return new ResponseEntity<>("Category added successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable long categoryId){
        try{
            String status=categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok(status);
        } catch(ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }

    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category,@PathVariable Long categoryId){
      try{
          Category savedCategory=categoryService.updateCategory(category,categoryId);
          System.out.println(savedCategory);
          return new ResponseEntity<>("Category with category id: "+categoryId,HttpStatus.OK);
      }
      catch (ResponseStatusException e){
          return new ResponseEntity<>(e.getReason(),e.getStatusCode());
      }
    }

}
