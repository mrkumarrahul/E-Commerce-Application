package com.ecommerce.project.payload;

public class ProductDto {

    private Long productId;
    private String productName;
    private String image;
    private Integer quantity;
    private double discount;
    private double price;
    private double specialPrice;

    // No-argument constructor
    public ProductDto() {
    }

    // Constructor with all fields
    public ProductDto(Long productId, String productName, String image,
                      Integer quantity, double discount, double price,
                      double specialPrice) {

        this.productId = productId;
        this.productName = productName;
        this.image = image;
        this.quantity = quantity;
        this.discount = discount;
        this.price = price;
        this.specialPrice = specialPrice;
    }

    // Getters

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getImage() {
        return image;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public double getPrice() {
        return price;
    }

    public double getSpecialPrice() {
        return specialPrice;
    }

    // Setters

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSpecialPrice(double specialPrice) {
        this.specialPrice = specialPrice;
    }

    // toString()

    @Override
    public String toString() {
        return "ProductDto{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", image='" + image + '\'' +
                ", quantity=" + quantity +
                ", discount=" + discount +
                ", price=" + price +
                ", specialPrice=" + specialPrice +
                '}';
    }
}