package com.flowerShop.api.controllers.dtos;

import lombok.*;

@Data
@Builder

public class APIResponse {

    @NonNull
    private final boolean isSuccessful;
    @NonNull
    private final String message;
    private final Object responseDTO; // TODO: 3/28/21 make others required but this guy

}
