package com.ecommerce.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

import javax.annotation.processing.Generated;

@Entity(name = "category")
public class Category {

    @Id
    @GeneratedValue
    public Long getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    private Long categoryId;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;

    }

    public Category(){

    }

    private String categoryName;

}
