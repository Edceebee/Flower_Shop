package com.flowerShop.api.models.User;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@Builder

public class User {

    @Id
    private String id;

    private String username;

    @NonNull
    private String primaryEmailAddress;
    @NonNull
    private String tel;
    @NonNull
    private String passwordHash;

    @DBRef
    private List<UserCategory> userCategories;

    private UserProfile profile;

    public void addCategories(char[] userCategories) {
        for (int i = 0; i < userCategories.length; i++) {
            switch (i) {
                case '1':
                    getUserCategories().add(UserCategory.BUYER);
                    break;

                case 2:
                    getUserCategories().add(UserCategory.SELLER);
                    break;

                case 3:
                    getUserCategories().add(UserCategory.BUYER);
                    getUserCategories().add(UserCategory.SELLER);
                    break;

                default:
                    // TODO: 3/28/21 throw user category not specified exception, level 2
            }
        }
    }

    public char[] getUserCategoryNumbers() {
        char[] userCategoryNumbers = new char[getUserCategories().size()];
        for (int i = 0; i < userCategoryNumbers.length; i++) {

            switch(userCategories.get(i)) {
                case BUYER:
                    userCategoryNumbers[i] = '1';
                    break;
                case SELLER:
                    userCategoryNumbers[i] = '2';
                    break;
                default:
            }

        }
        return userCategoryNumbers;
    }
}
