package com.springPractice.productcatalog.model;


import javax.validation.constraints.*;

public class ProductDTO {
    @Digits(integer = 3, fraction = 0, message = "Product id can only be integer")
    private int productId;

    @NotBlank(message = "Product name not found")
    @NotNull(message = "Product name can not be null")
    private String productName;

    @Positive(message = "Product price should be more than 0")
    @Digits(integer = 10, fraction = 2, message = "Rate should be digits only")
    private double productRate;

    public ProductDTO() {
        super();
    }

    public ProductDTO(int productId, String productName, double productRate) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.productRate = productRate;
    }

    public ProductDTO(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductRate() {
        return productRate;
    }

    public void setProductRate(double productRate) {
        this.productRate = productRate;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productRate=" + productRate +
                '}';
    }
}
