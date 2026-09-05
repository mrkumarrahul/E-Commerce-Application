package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.payload.ProductDto;
import com.ecommerce.project.payload.ProductResponse;
import com.ecommerce.project.repository.CategoryRepository;
import com.ecommerce.project.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

//    @Autowired
//    private Category category;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductDto addProduct(Long categoryId, Product product) {
        Category category= categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));

        product.setCategory(category);
        double specialPrice=product.getPrice()-(product.getPrice()*(product.getDiscount()*0.01));
        product.setImage("default.png");
        product.setSpecialPrice(specialPrice);
        product.setQuantity(product.getQuantity());
        Product savedProduct= productRepository.save(product);
        return modelMapper.map(savedProduct,ProductDto.class);
    }

    @Override
    public ProductResponse getAllProducts() {
        List<Product> products=productRepository.findAll();
        List<ProductDto> productDtos= products.stream()
                .map(product -> modelMapper.map(product,ProductDto.class))
                .toList();
        ProductResponse productResponse=new ProductResponse();
        productResponse.setContent(productDtos);
        return productResponse;
    }

    @Override
    public ProductResponse getProductByCategory(Long categoryId) {
        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Category","categoryId",categoryId));

        List<Product> products= productRepository.findByCategoryOrderByPriceAsc(category);
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product,ProductDto.class))
                .toList();
        ProductResponse productResponse=new ProductResponse();
        productResponse.setContent(productDtos);
        return productResponse;
    }
}
