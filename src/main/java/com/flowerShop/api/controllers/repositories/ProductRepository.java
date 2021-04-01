package com.flowerShop.api.controllers.repositories;

import com.flowerShop.api.controllers.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
      Optional<Product> findProductById(String id);

}
