package com.springPractice.productcatalog.service;

import com.springPractice.productcatalog.exception.ProductCatalogException;
import com.springPractice.productcatalog.model.ProductDTO;
import com.springPractice.productcatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<ProductDTO> getProductsList() {
        return repository.getProductsList();
    }

    @Override
    public ProductDTO getProduct(int productId) {
        return repository.getProduct(productId);
    }

    @Override
    public String addProduct(ProductDTO product) {
        return repository.addProduct(product);
    }

    @Override
    public String updateProduct(ProductDTO product) {
        return repository.updateProduct(product);
    }

    @Override
    public ProductDTO deleteProduct(int productId) throws ProductCatalogException {
        return repository.deleteProduct(productId);
    }
}
