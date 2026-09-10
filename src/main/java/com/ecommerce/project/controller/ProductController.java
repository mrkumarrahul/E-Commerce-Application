package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.payload.ProductDto;
import com.ecommerce.project.payload.ProductResponse;
import com.ecommerce.project.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/productDto")
    public ResponseEntity<ProductDto> addProduct(@Valid @RequestBody ProductDto productDto, @PathVariable Long categoryId){
         ProductDto savedProductDto= productService.addProduct(categoryId,productDto);
         return new ResponseEntity<>(savedProductDto, CREATED);
    }

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestParam(name="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber
            ,@RequestParam(name = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize
            ,@RequestParam(name="sortBy",defaultValue=AppConstants.SORT_CATEGORIES_BY,required = false) String sortBy
            ,@RequestParam(name = "sortOrder",defaultValue=AppConstants.SORT_DIR,required=false) String sortOrder
    ){

        ProductResponse productResponse= productService.getAllProducts(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/products/{categoryId}")
    public ResponseEntity<ProductResponse> getProductByCategory(@PathVariable Long categoryId,
                                                                @RequestParam(name="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber
            ,@RequestParam(name = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize
            ,@RequestParam(name="sortBy",defaultValue=AppConstants.SORT_CATEGORIES_BY,required = false) String sortBy
            ,@RequestParam(name = "sortOrder",defaultValue=AppConstants.SORT_DIR,required=false) String sortOrder){
        ProductResponse productResponse=productService.getProductByCategory(categoryId,pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductByKeyword(@PathVariable String keyword,Long categoryId,
                                                               @RequestParam(name="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber
            ,@RequestParam(name = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize
            ,@RequestParam(name="sortBy",defaultValue=AppConstants.SORT_CATEGORIES_BY,required = false) String sortBy
            ,@RequestParam(name = "sortOrder",defaultValue=AppConstants.SORT_DIR,required=false) String sortOrder){
        ProductResponse productResponse=productService.getProductByKeyword(keyword,categoryId,pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(productResponse,HttpStatus.FOUND);
    }

    @PutMapping("/public/products/update/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId,@Valid @RequestBody ProductDto productDto){
        ProductDto updatedProductDto= productService.updateProduct(productId,productDto);
        return new ResponseEntity<>(updatedProductDto,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/public/product/{productId}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long productId){
        ProductDto deletedProduct=productService.deleteProduct(productId);
        return new ResponseEntity<>(deletedProduct,HttpStatus.OK);
    }

    @PutMapping("/public/product/image/{productId}")
    public ResponseEntity<ProductDto> updateProductImage(@PathVariable Long productId,
                                                         @RequestParam("Image") MultipartFile image) throws IOException {
        ProductDto updatedProduct= productService.updateProductImage(productId,image);
        return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
    }

}
