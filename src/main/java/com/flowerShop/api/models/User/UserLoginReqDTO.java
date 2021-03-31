package com.flowerShop.api.models.User;


import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder

public class UserLoginReqDTO {

    private String username;

    @NonNull
    private String emailAddress;

    @NonNull
    private String passWord;

}
