package com.flowerShop.web.controllers;

import com.flowerShop.api.controllers.dtos.ApiResponse;
import com.flowerShop.api.controllers.dtos.ProductDTO;
import com.flowerShop.api.controllers.models.Product;
import com.flowerShop.api.controllers.repositories.ProductRepository;
import com.flowerShop.api.controllers.services.ProductException;
import com.flowerShop.api.controllers.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.PathMatcher;

@RequestMapping("/products/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("create")
     public ResponseEntity<?> createAProduct(@RequestBody ProductDTO productDTO){
         productService.createNewProduct(productDTO);
         return new ResponseEntity<>(new ApiResponse(true,"Product Successfully Created"), HttpStatus.CREATED);
     }
     @RequestMapping("remove_product")
     public ResponseEntity<?> deleteProduct(@RequestBody Product product){
        productService.deleteProduct(product);
        return  new ResponseEntity<>(new ApiResponse(true,"Product Removed Successfully"),HttpStatus.GONE);
     }
     @RequestMapping("all_products")
     public ResponseEntity<?> getALlProducts(){
        productService.getAllProducts();
        return new ResponseEntity<>(new ApiResponse(true,"ALL PRODUCTS"),HttpStatus.FOUND);
     }
     @RequestMapping("{id}/get_a_product")
     public ResponseEntity<?> findProductById(@RequestBody @PathVariable String id){
         productRepository.findProductById(id);
         return new ResponseEntity<>(new ApiResponse(true,"Product Found"),HttpStatus.FOUND);
     }

     @RequestMapping("{id}/get_a_product")
    public ResponseEntity<?> getAProduct(@RequestBody @PathVariable String id){
         try {
             productService.findProductById(id);
         return  new ResponseEntity<>(new ApiResponse(true,"Product Found Successfully"),HttpStatus.FOUND);
         } catch (ProductException e) {
             e.printStackTrace();
         }
         return new ResponseEntity<>(new ApiResponse(false,"Product NOT Found"),HttpStatus.NOT_FOUND);
     }



}
