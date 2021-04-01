package com.flowerShop.api.controllers;

import com.flowerShop.api.controllers.dtos.APIResponse;
import com.flowerShop.api.controllers.dtos.APIResponseMessage;
import com.flowerShop.api.controllers.dtos.user.UserRegReqDTO;
import com.flowerShop.api.controllers.dtos.user.UserDetailsRespDTO;
import com.flowerShop.api.models.User.UserLoginReqDTO;
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
    public ResponseEntity<APIResponse> register(@RequestBody UserRegReqDTO userRegReqDTO) {
        try {
            UserDetailsRespDTO userDetailsRespDTO = userService.register(userRegReqDTO);
            return new ResponseEntity<>(
                    APIResponse.builder()
                            .isSuccessful(true)
                            .message(APIResponseMessage.USER_REGISTRATION_SUCCESS.getMessage())
                            .responseDTO(userDetailsRespDTO)
                            .build(),
                    HttpStatus.CREATED
            );
        }
        catch (UserException userException) {
            return new ResponseEntity<>(
                    APIResponse.builder()
                            .isSuccessful(false)
                            .message(userException.getMessage())
                            .build(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody UserLoginReqDTO userLoginReqDTO) {
        try {
            UserDetailsRespDTO userLoginRespDTO = userService.login(userLoginReqDTO);
            return new ResponseEntity<>(
                    APIResponse.builder()
                            .isSuccessful(true)
                            .message(APIResponseMessage.USER_LOGIN_SUCCESS.getMessage())
                            .responseDTO(userLoginRespDTO)
                            .build(),
                    HttpStatus.OK
            );
        }
        catch (UserException userException) {
            return new ResponseEntity<>(
                    APIResponse.builder()
                            .isSuccessful(false)
                            .message(userException.getMessage())
                            .build(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
