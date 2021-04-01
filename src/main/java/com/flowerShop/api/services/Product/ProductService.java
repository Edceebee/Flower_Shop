package com.flowerShop.api.services.Product;

import com.flowerShop.api.controllers.dtos.product.ProductDTO;
import com.flowerShop.api.models.Product.Product;
import com.flowerShop.exceptions.ProductException;

import java.util.List;

public interface ProductService {
    Product createNewProduct(ProductDTO productDTO);
    void deleteProduct(ProductDTO productDTO);
    List<Product> getAllProducts();
    Product findProductById(String id) throws ProductException;

}
