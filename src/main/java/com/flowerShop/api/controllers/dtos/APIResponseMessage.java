package com.flowerShop.api.controllers.dtos;

public enum APIResponseMessage {

    USER_REGISTRATION_SUCCESS("User Successfully Registered"),
    USER_LOGIN_SUCCESS("User Login Successful");

    private final String message;

    APIResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
