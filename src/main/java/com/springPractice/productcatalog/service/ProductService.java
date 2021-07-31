package com.springPractice.productcatalog.service;

import com.springPractice.productcatalog.exception.ProductCatalogException;
import com.springPractice.productcatalog.model.ProductDTO;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> getProductsList();

    public ProductDTO getProduct(int productId);

    public String addProduct(ProductDTO product);

    public String updateProduct(ProductDTO product);

    public ProductDTO deleteProduct(int productId) throws ProductCatalogException;
}
