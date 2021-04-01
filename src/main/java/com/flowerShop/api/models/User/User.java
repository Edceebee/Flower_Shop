package com.flowerShop.api.models.User;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.ByteArrayInputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.time.LocalDateTime;
import java.util.Arrays;
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
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    public static String hashPassword(String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            try {
                byte[] hash = factory.generateSecret(spec).getEncoded();

                StringBuilder buffer = new StringBuilder();

                for(byte hashValue: hash) {
                    buffer.append(hashValue);
                }

                String hashedPassword = buffer.toString();
            }
            catch (InvalidKeySpecException exception) {
                // TODO: 3/30/21 Use Logger!
                System.err.println("Wahala dey oo!");
            }
        }
        catch (NoSuchAlgorithmException exception) {
            System.err.println("Double Wahala!");
        }

        return password;
    }

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
                    userCategoryNumbers[i] = UserCategory.BUYER.getFlag();
                    break;
                case SELLER:
                    userCategoryNumbers[i] = UserCategory.SELLER.getFlag();
                    break;
                default:
            }

        }
        return userCategoryNumbers;
    }
}
