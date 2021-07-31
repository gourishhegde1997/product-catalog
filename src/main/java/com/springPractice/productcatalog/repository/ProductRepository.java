package com.springPractice.productcatalog.repository;

import com.springPractice.productcatalog.exception.ProductCatalogException;
import com.springPractice.productcatalog.model.ProductDTO;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class ProductRepository {
    private List<ProductDTO> productsList;

    @PostConstruct
    public void init() {
        productsList = new ArrayList<>();
        productsList.add(new ProductDTO(1, "T-Shirt", 350.00));
        productsList.add(new ProductDTO(2, "Jeans Pant", 850.00));
        productsList.add(new ProductDTO(3, "Formal Shirt", 1300.00));
        productsList.add(new ProductDTO(4, "Sneaker", 300.00));
        productsList.add(new ProductDTO(5, "Hat", 190.00));
    }

    public List<ProductDTO> getProductsList() {
        return productsList;
    }

    public ProductDTO getProduct(int productId) {
        Optional<ProductDTO> optional = productsList.stream().filter(c -> c.getProductId() == productId).findFirst();
        return optional.get();
    }

    public String addProduct(ProductDTO product) {
        productsList.add(product);
        return "success";
    }

    public String updateProduct(ProductDTO product) {
        for (ProductDTO p : productsList) {
            if(p.getProductId() == product.getProductId()) {
                productsList.remove(p);
                break;
            }
        }
        productsList.add(product);
        return "success";
    }

    public ProductDTO deleteProduct(int productId) throws ProductCatalogException {
        Optional<ProductDTO> optional = productsList.stream().filter(c -> c.getProductId() == productId).findFirst();
        try {
            ProductDTO productDTO = optional.get();
            productsList.remove(productDTO);
            return  productDTO;
        } catch (NoSuchElementException e) {
            throw new ProductCatalogException("REPOSITORY.PRODUCT_ID_NOT_FOUND");
        }
    }
}
