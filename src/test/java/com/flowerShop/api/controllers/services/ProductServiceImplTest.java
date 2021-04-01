package com.flowerShop.api.controllers.services;

import com.flowerShop.api.controllers.dtos.ProductDTO;
import com.flowerShop.api.controllers.models.Product;
import com.flowerShop.api.controllers.repositories.ProductRepository;
import lombok.Data;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;


    @InjectMocks
    private ProductService productService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void tearDown() {
    }
@Test
    void testToCreateAndSaveNewProduct(){
    Product newProduct = new Product();

    newProduct.setId("ID001");
    newProduct.setProductPrice(2000);
    newProduct.setProductName("Coaster Biscuits");
    newProduct.setProductDescription("Honey Biscuits");

    ProductDTO pDTO = new ProductDTO();
    pDTO.setProductName("Coaster Biscuits");
    pDTO.setProductPrice(2000);
    pDTO.setProductDescription("Honey Biscuits");

    when(productRepository.save(new Product())).thenReturn(newProduct); //save original product in repo, then return product
    Product savedProduct = productService.createNewProduct(pDTO); //pass in productDTO as in the product_services methods to set the template for the original prodct
    assertEquals(newProduct,savedProduct); //check that pDTO IS equal to the original product
}
@Test
    void testToDeleteAProduct(){
        ProductDTO pDTO = new ProductDTO();
        pDTO.setProductName("Coaster Biscuits");
        pDTO.setProductPrice(2000);
        pDTO.setProductDescription("Honey Biscuits");

        Product savedProduct = productService.createNewProduct(pDTO);
        productService.deleteProduct(savedProduct);
        assertEquals(1,productService.getAllProducts().size());
}
@Test
    void testToFindProductBYId(){
    Product newProduct = new Product();

    newProduct.setId("ID001");
    newProduct.setProductPrice(2000);
    newProduct.setProductName("Coaster Biscuits");
    newProduct.setProductDescription("Honey Biscuits");
    Product savedProduct = productRepository.save(newProduct);

    Optional<Product> foundProduct = Optional.ofNullable(productService.findProductById(savedProduct.getId()));
    assertNotNull(foundProduct.get());
    foundProduct.ifPresent(product -> assertEquals("ID001", product.getId()));
    //OR
    /*if(foundProduct.isPresent()) {
        assertEquals("ID001", foundProduct.get().getId());
    }*/



}


}