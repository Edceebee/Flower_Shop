package com.flowerShop.api.controllers.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductDTO {
    private String productName;
    private double productPrice;
    private  String productDescription;


}
