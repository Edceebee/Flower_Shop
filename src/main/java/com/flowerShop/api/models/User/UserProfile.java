package com.flowerShop.api.models.User;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserProfile {

    private String firstName;
    private String lastName;
    private String middleName;
    private Date dateOfBirth;

    private List<Address> addresses;

}
