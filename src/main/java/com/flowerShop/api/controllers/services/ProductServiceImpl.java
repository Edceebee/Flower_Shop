package com.flowerShop.api.controllers.services;

import com.flowerShop.api.controllers.dtos.ProductDTO;
import com.flowerShop.api.controllers.models.Product;
import com.flowerShop.api.controllers.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createNewProduct(ProductDTO productDTO) {
        Product newProduct = new Product();
        newProduct.setProductName(productDTO.getProductName());
        newProduct.setProductPrice(productDTO.getProductPrice());
        return saveProduct(newProduct);
    }
    @Override
    public void deleteProduct(Product product) {
      productRepository.delete(product);
    }

    @Override
    public List<Product> getAllProducts() {
     return productRepository.findAll();
    }

    @Override
    public Product getProduct(String id) throws ProductException {
        Product savedProduct = new Product();
        Product foundProduct = findProductById(id);
        if(savedProduct.getId().equals(foundProduct.getId())){
            return foundProduct;
        }
        else{
            throw  new ProductException("Product NOT FOUND");
        }
    }
    @Override
    public Product findProductById(String id) throws ProductException {
        Optional<Product> foundProduct = productRepository.findProductById(id);
        if(foundProduct.isPresent()){
            return  foundProduct.get();
        }else{
            throw new ProductException("Product NOT FOUND");
        }
    }
    private Product saveProduct(Product product){
        return productRepository.save(product);
    }

}
