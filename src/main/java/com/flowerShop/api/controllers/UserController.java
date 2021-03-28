package com.flowerShop.api.controllers;

import com.flowerShop.api.controllers.dtos.APIResponse;
import com.flowerShop.api.controllers.dtos.APIResponseMessage;
import com.flowerShop.api.controllers.dtos.user.UserRegReqDTO;
import com.flowerShop.api.controllers.dtos.user.UserRegRespDTO;
import com.flowerShop.api.services.User.UserService;
import com.flowerShop.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegReqDTO userRegReqDTO) {
        try {
            UserRegRespDTO userRegRespDTO = userService.register(userRegReqDTO);
            return new ResponseEntity<>(
                    APIResponse.builder()
                            .isSuccessful(true)
                            .message(APIResponseMessage.USER_REGISTRATION_SUCCESS.toString())
                            .responseDTO(userRegRespDTO)
                            .build(),
                    HttpStatus.CREATED
            );
        }
        catch (UserException userException) {
            return new ResponseEntity<>(
                    APIResponse.builder()
                            .isSuccessful(false)
                            .message(APIResponseMessage.USER_REGISTRATION_SUCCESS.toString())
                            .build(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
