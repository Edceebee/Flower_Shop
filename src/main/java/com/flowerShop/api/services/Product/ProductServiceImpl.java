package com.flowerShop.api.services.Product;

import com.flowerShop.api.controllers.dtos.product.ProductDTO;
import com.flowerShop.api.models.Product.Product;
import com.flowerShop.api.repositories.product.ProductRepository;
import com.flowerShop.api.services.Product.ProductService;
import com.flowerShop.exceptions.ProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

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
    public void deleteProduct(ProductDTO productDTO) {
      productRepository.delete(productDTO);
    }

    @Override
    public List<Product> getAllProducts() {
     return productRepository.findAll();
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
