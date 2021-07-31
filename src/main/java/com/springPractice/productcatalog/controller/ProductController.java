package com.springPractice.productcatalog.controller;

import com.springPractice.productcatalog.exception.ExceptionClass;
import com.springPractice.productcatalog.exception.ProductCatalogException;
import com.springPractice.productcatalog.model.ProductDTO;
import com.springPractice.productcatalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@Validated
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(path = "/getAllProducts")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDTOList = service.getProductsList();
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @GetMapping(path = "/getProduct/{productId}", params = "version=1")
    public ResponseEntity<ProductDTO> getProductByIdv1(@PathVariable @Digits(integer = 3, fraction = 0, message = "Id can only be digit") int productId) {
        ProductDTO product = service.getProduct(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping(path = "/getProduct/{productId}", params = "version=2")
    public ResponseEntity<ProductDTO> getProductByIdv2(@PathVariable @Digits(integer = 3, fraction = 0, message = "Id can only be digit") int productId) {
        ProductDTO product = service.getProduct(productId);
        return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/addProduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addProduct(@Valid @RequestBody ProductDTO product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "));
            ExceptionClass e = new ExceptionClass(HttpStatus.NOT_ACCEPTABLE.value(), message);
            return new ResponseEntity<>(e, HttpStatus.NOT_ACCEPTABLE);
        } else {
            String result = service.addProduct(product);
            return ResponseEntity.ok(result);
        }

    }

    @PutMapping("/updateProduct")
    public ResponseEntity<String> updateProduct(@Valid @RequestBody ProductDTO product, Errors error) {
        String result = service.updateProduct(product);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path = "/deleteProduct")
    public ResponseEntity<ProductDTO> deleteProductById(@RequestParam int productId) throws ProductCatalogException {
        ProductDTO product = service.deleteProduct(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


}
