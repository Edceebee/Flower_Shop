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

    private String getMessage() {
        return message;
    }

    /**
     * Returns the name of this enum constant, as contained in the
     * declaration.  This method may be overridden, though it typically
     * isn't necessary or desirable.  An enum type should override this
     * method when a more "programmer-friendly" string form exists.
     *
     * @return the name of this enum constant
     */
    @Override
    public String toString() {
        return this.getMessage();
    }
}
