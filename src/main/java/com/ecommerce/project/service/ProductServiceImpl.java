package com.ecommerce.project.service;

import com.ecommerce.project.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.payload.ProductDto;
import com.ecommerce.project.payload.ProductResponse;
import com.ecommerce.project.repository.CategoryRepository;
import com.ecommerce.project.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Autowired
    FileService fileService;

    @Value("${project.image}")
    private String path;

    @Override
    public ProductDto addProduct(Long categoryId, ProductDto productDto) {
        Category category= categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));

        boolean isProductNotPresent=true;

        List<Product> products=category.getProducts();

        for(int i=0;i<products.size();i++){
            if(products.get(i).getProductName().equals(productDto.getProductName())){
                isProductNotPresent=false;
                break;
            }
        }

        if(isProductNotPresent) {

            Product product = modelMapper.map(productDto, Product.class);

            product.setCategory(category);
            double specialPrice = product.getPrice() - (product.getPrice() * (product.getDiscount() * 0.01));
            product.setImage("default.png");
            product.setSpecialPrice(specialPrice);
            product.setQuantity(product.getQuantity());
            Product savedProduct = productRepository.save(product);
            return modelMapper.map(savedProduct, ProductDto.class);
        }
        else {
            throw new APIException("Product already exist!!");
        }
    }

    @Override
    public ProductResponse getAllProducts(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder) {
        Sort sortByAndOrder=sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageDetails= PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Product> categoryPage=productRepository.findAll(pageDetails);
        List<Product> products=categoryPage.getContent();
        if(products.isEmpty()){
            throw new APIException("No Product is available");
        };

        List<ProductDto> productDtos= products.stream()
                .map(product -> modelMapper.map(product,ProductDto.class))
                .toList();

        ProductResponse productResponse=new ProductResponse();
        productResponse.setContent(productDtos);
        productResponse.setContent(productDtos);
        productResponse.setPageNumber(categoryPage.getNumber());
        productResponse.setPageSize(categoryPage.getTotalPages());
        productResponse.setTotalPages(categoryPage.getTotalPages());
        productResponse.setTotalElements(categoryPage.getTotalElements());
        productResponse.setLastPage(categoryPage.isLast());

        return productResponse;
    }

    @Override
    public ProductResponse getProductByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Category","categoryId",categoryId));

        Sort sortByAndOrder=sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageDetails= PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Product> categoryPage=productRepository.findByCategoryOrderByPriceAsc(category,pageDetails);
        List<Product> products=categoryPage.getContent();

        if(products.isEmpty()){
            throw new APIException(category.getCategoryName()+" does not have any products:");
        }

        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product,ProductDto.class))
                .toList();
        ProductResponse productResponse=new ProductResponse();
        productResponse.setContent(productDtos);
        productResponse.setContent(productDtos);
        productResponse.setPageNumber(categoryPage.getNumber());
        productResponse.setPageSize(categoryPage.getTotalPages());
        productResponse.setTotalPages(categoryPage.getTotalPages());
        productResponse.setTotalElements(categoryPage.getTotalElements());
        productResponse.setLastPage(categoryPage.isLast());
        return productResponse;
    }

    @Override
    public ProductResponse getProductByKeyword(String keyword,Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder=sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageDetails= PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Product> categoryPage=productRepository.findByProductNameLikeIgnoreCase('%' + keyword +'%',pageDetails);
        List<Product> products= categoryPage.getContent();
        List<ProductDto> productDtos=products.stream()
                .map(product -> modelMapper.map(product,ProductDto.class))
                .toList();

        if(products.isEmpty()){
            throw new APIException("Products not found with keyword :"+keyword);
        }

        ProductResponse productResponse=new ProductResponse();
        productResponse.setContent(productDtos);
        productResponse.setContent(productDtos);
        productResponse.setContent(productDtos);
        productResponse.setPageNumber(categoryPage.getNumber());
        productResponse.setPageSize(categoryPage.getTotalPages());
        productResponse.setTotalPages(categoryPage.getTotalPages());
        productResponse.setTotalElements(categoryPage.getTotalElements());
        productResponse.setLastPage(categoryPage.isLast());
        return productResponse;
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        Product productFromDB=productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Product","ProductId",productId));
        Product product=modelMapper.map(productDto,Product.class);
        productFromDB.setProductName(product.getProductName());
        productFromDB.setDescription(product.getDescription());
        productFromDB.setQuantity(product.getQuantity());
        productFromDB.setImage(product.getImage());
        productFromDB.setPrice(product.getPrice());
        productFromDB.setSpecialPrice(product.getSpecialPrice());
        productFromDB.setDiscount(product.getDiscount());
        Product savedProduct=productRepository.save(productFromDB);
        return modelMapper.map(savedProduct,ProductDto.class);
    }

    @Override
    public ProductDto deleteProduct(Long productId) {
        Product productDelete=productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Product","productId",productId));

        productRepository.delete(productDelete);

        return modelMapper.map(productDelete,ProductDto.class);
    }

    @Override
    public ProductDto updateProductImage(Long productId, MultipartFile image) throws IOException {
        Product productFromDb=productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Product","productId",productId));
        String path="images/";
        String fileName= fileService.uploadImage(path,image);



        productFromDb.setImage(fileName);
        Product updatedProduct=productRepository.save(productFromDb);

        return modelMapper.map(updatedProduct,ProductDto.class);
    }

}
