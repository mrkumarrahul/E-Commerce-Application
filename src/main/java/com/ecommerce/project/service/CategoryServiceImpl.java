package com.ecommerce.project.service;

import com.ecommerce.project.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    @Override
    public List<Category> getAllCategories(){
        List<Category> categories=categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new APIException("No category is available: ");
        };
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category){
//        category.setCategoryId(nextId++);
        Category savedCategory=categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory!=null){
            throw new APIException("Category with name: "+category.getCategoryName()+" already exists !!!");
        }
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId){
        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException(
                       "Category","CategoryId",categoryId));

        categoryRepository.delete(category);
                 return "Category with categoryId: "+categoryId+ " deleted successfully !!";
    }

    @Override
    public Category updateCategory(Category category,Long categoryId){
        Category category1=categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException(
                                "Category","CategoryId",categoryId));

        category1.setCategoryName(category.getCategoryName());
            return categoryRepository.save(category1);
        }

}
