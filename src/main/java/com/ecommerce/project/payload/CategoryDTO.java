package com.ecommerce.project.payload;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jdk.jfr.DataAmount;

public class CategoryDTO {
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

    public CategoryDTO(String categoryName) {
        this.categoryName = categoryName;

    }

    public CategoryDTO(){

    }

    @Size(min = 5,message = "Category must contain be at least 5 character")
    @NotBlank
    private String categoryName;
}
