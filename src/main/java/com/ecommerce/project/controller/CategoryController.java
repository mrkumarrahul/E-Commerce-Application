package com.ecommerce.project.controller;


import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @GetMapping("/get")
    public ResponseEntity<String> echoMessage(@RequestParam(name = "message") String message){
        return new ResponseEntity<>("Echoed message: "+message,HttpStatus.OK);
    }

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    private CategoryServiceImpl categoryService;

    @GetMapping("api/public/categories")
    public ResponseEntity<CategoryResponse> getAllcategories(@RequestParam(name="pageNumber") Integer pageNumber
    ,@RequestParam(name="pageSize") Integer pageSize){
        CategoryResponse categoryResponse= categoryService.getAllCategories(pageNumber,pageSize);
        return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
    }


    @PostMapping("/api/public/categories")
    public ResponseEntity<CategoryDTO> createcategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO categoryDTO1= categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(categoryDTO1,HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable long categoryId){
            CategoryDTO deletedCategory=categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(deletedCategory,HttpStatus.OK);
    }

    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable Long categoryId){
          CategoryDTO savedCategoryDTO=categoryService.updateCategory(categoryDTO,categoryId);
          return new ResponseEntity<>(savedCategoryDTO,HttpStatus.OK);
      }
    }


