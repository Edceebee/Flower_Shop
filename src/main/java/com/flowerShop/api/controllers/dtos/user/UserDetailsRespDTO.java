package com.flowerShop.api.controllers.dtos.user;

import com.flowerShop.api.models.User.Address;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder

public class UserDetailsRespDTO {

    private String username;
    @NonNull
    private String emailAddress;
    @NonNull
    private String tel;
    @NonNull
    private char[] userCategoryNumbers; /* 1 for BUYER, 2 for SELLER */

    private String firstName;
    private String lastName;
    private String middleName;
    private Date dateOfBirth;

    private List<Address> addresses;

}
