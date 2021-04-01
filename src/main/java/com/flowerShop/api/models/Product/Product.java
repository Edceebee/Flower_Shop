package com.flowerShop.api.models.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Product {
    @Id
    private String id;

    private String productDescription;
    private String productName;
    private double productPrice;

}
