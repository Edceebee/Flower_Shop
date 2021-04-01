package com.flowerShop.api.controllers;

import com.flowerShop.api.controllers.dtos.APIResponse;
import com.flowerShop.api.controllers.dtos.APIResponseMessage;
import com.flowerShop.api.controllers.dtos.product.ProductDTO;
import com.flowerShop.api.models.Product.Product;
import com.flowerShop.api.repositories.product.ProductRepository;
import com.flowerShop.api.services.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/products/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("create")
     public ResponseEntity<?> createAProduct(@RequestBody ProductDTO productDTO){
         productService.createNewProduct(productDTO);
         return new ResponseEntity<>(APIResponse.builder()
                 .message(APIResponseMessage.PRODUCT_CREATED_SUCCESSFULLY.toString())
                 .responseDTO(productDTO)
                 .build(),
                 HttpStatus.CREATED);
     }
     @RequestMapping("remove_product")
     public ResponseEntity<?> deleteProduct(@RequestBody ProductDTO productDTO){
        productService.deleteProduct(productDTO);
        return  new ResponseEntity<>(APIResponse.builder()
                .isSuccessful(true)
                .message(APIResponseMessage.PRODUCT_DELETED_SUCCESSFULLY.toString())
                .responseDTO(productDTO)
                .build(),
                HttpStatus.GONE);
     }
     @RequestMapping("all_products")
     public ResponseEntity<?> getALlProducts(){
        productService.getAllProducts();
        return new ResponseEntity<>(APIResponse.builder()
                    .isSuccessful(true)
                    .message(APIResponseMessage.ALL_PRODUCTS.toString())
                    .responseDTO(productRepository)
                    .build(),
                HttpStatus.FOUND);
     }
     @RequestMapping("{id}/get_a_product")
     public ResponseEntity<?> findProductById(@PathVariable String id){
         productRepository.findProductById(id);
         return  new ResponseEntity<>(APIResponse.builder()
                                    .isSuccessful(true)
                                    .message(APIResponseMessage.PRODUCT_FOUND_SUCCESSFULLY.toString())
                                    .responseDTO(productRepository.findProductById(id))
                                    .build(),HttpStatus.FOUND);

     }



}
