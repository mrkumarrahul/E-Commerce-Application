package com.ecommerce.project.payload;

import java.util.List;

public class CategoryResponse {

    private List<CategoryDTO> content;

    // No-Args Constructor
    public CategoryResponse() {
    }

    // All-Args Constructor
    public CategoryResponse(List<CategoryDTO> content) {
        this.content = content;
    }

    // Getter
    public List<CategoryDTO> getContent() {
        return content;
    }

    // Setter
    public void setContent(List<CategoryDTO> content) {
        this.content = content;
    }
}