package com.flowerShop.api.models.User;

public enum UserCategory {

    BUYER('1'),
    SELLER('2');

    private final char flag;

    UserCategory(char flag) {
        this.flag = flag;
    }

    public char getFlag() {
        return flag;
    }
}
