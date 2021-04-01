package com.flowerShop.api.repositories.product;

import com.flowerShop.api.controllers.dtos.product.ProductDTO;
import com.flowerShop.api.models.Product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
      Optional<Product> findProductById(String id);

      void delete(ProductDTO productDTO);
}
