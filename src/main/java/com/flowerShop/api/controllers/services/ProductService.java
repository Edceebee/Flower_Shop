package com.flowerShop.api.controllers.services;

import com.flowerShop.api.controllers.dtos.ProductDTO;
import com.flowerShop.api.controllers.models.Product;

import java.util.List;

public interface ProductService {
    Product createNewProduct(ProductDTO productDTO);
    void deleteProduct(Product product);
    List<Product> getAllProducts();
    Product getProduct(String id) throws ProductException;
    Product findProductById(String id) throws ProductException;

}
