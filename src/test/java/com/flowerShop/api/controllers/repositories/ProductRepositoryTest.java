package com.flowerShop.api.controllers.repositories;

import com.flowerShop.api.controllers.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataMongoTest
class ProductRepositoryTest {
    private Product product;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductName("Coaster Biscuits");
        product.setProductDescription("Honey Biscuits");
        product.setProductPrice(200.0);
        product.setId("ID001");
    }

    @AfterEach
    void tearDown() {
    }
@Test
    void testToSave(){
        Product savedProduct = productRepository.save(product);
        assertNotNull(savedProduct.getId());
        assertEquals(product,savedProduct);
}
@Test
    void testToFindProductByID(){
        Product savedProduct = productRepository.save(product);
        Optional<Product> foundProduct = productRepository.findProductById(savedProduct.getId());
        assertEquals(savedProduct,foundProduct.orElse(null));
}
}