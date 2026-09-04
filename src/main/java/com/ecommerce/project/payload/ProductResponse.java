package com.ecommerce.project.payload;

import java.util.List;

public class ProductResponse {

    private List<ProductDto> content;

    // No-argument constructor
    public ProductResponse() {
    }

    // All-argument constructor
    public ProductResponse(List<ProductDto> content) {
        this.content = content;
    }

    // Getter
    public List<ProductDto> getContent() {
        return content;
    }

    // Setter
    public void setContent(List<ProductDto> content) {
        this.content = content;
    }

    // toString
    @Override
    public String toString() {
        return "ProductResponse{" +
                "content=" + content +
                '}';
    }
}