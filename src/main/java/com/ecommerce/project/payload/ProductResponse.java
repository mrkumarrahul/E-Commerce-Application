package com.ecommerce.project.payload;

import java.util.List;

public class ProductResponse {

    private List<ProductDto> content;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize(int totalPages) {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Boolean getLastPage() {
        return lastPage;
    }

    public void setLastPage(Boolean lastPage) {
        this.lastPage = lastPage;
    }

    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean lastPage;


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