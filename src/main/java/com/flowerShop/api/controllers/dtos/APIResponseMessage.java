package com.flowerShop.api.controllers.dtos;

public enum APIResponseMessage {

    USER_REGISTRATION_SUCCESS("User Successfully Registered"),
    USER_LOGIN_SUCCESS("User Login Successful"),
    PRODUCT_CREATED_SUCCESSFULLY("Product Successfully Created"),
    PRODUCT_DELETED_SUCCESSFULLY("Product Successfully Deleted"),
    PRODUCT_FOUND_SUCCESSFULLY("Product Found Successfully"),
    ALL_PRODUCTS("All Products");


    private final String message;

    APIResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
