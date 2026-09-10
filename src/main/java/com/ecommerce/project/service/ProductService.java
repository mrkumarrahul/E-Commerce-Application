package com.ecommerce.project.service;

import com.ecommerce.project.payload.ProductDto;
import com.ecommerce.project.payload.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {
    ProductDto addProduct(Long categoryId, ProductDto product);

    ProductResponse getAllProducts(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder);

    ProductResponse getProductByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ProductResponse getProductByKeyword(String keyword,Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ProductDto updateProduct(Long productId, ProductDto productDto);

    ProductDto deleteProduct(Long productId);

    ProductDto updateProductImage(Long productId, MultipartFile image) throws IOException;
}
