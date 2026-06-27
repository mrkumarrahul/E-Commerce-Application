package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    private List<Category> categories=new ArrayList<>();

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    private long nextId=1L;

    @Override
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category){
//        category.setCategoryId(nextId++);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId){
        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Resource not found"
                ));
        if(category==null){
            return "category not found";
        }
        categoryRepository.delete(category);
                 return "Category with categoryId: "+categoryId+ " deleted successfully !!";
    }

    @Override
    public Category updateCategory(Category category,Long categoryId){
        Category category1=categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Category not found"
                ));

        category1.setCategoryName(category.getCategoryName());
            return categoryRepository.save(category1);
        }




}
