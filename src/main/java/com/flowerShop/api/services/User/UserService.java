package com.flowerShop.api.services.User;

import com.flowerShop.api.controllers.dtos.user.UserRegReqDTO;
import com.flowerShop.api.controllers.dtos.user.UserDetailsRespDTO;
import com.flowerShop.api.models.User.UserLoginReqDTO;
import com.flowerShop.api.exceptions.UserException;

public interface UserService {

    UserDetailsRespDTO register(UserRegReqDTO userRegDTO) throws UserException;
    UserDetailsRespDTO login(UserLoginReqDTO userLoginReqDTO) throws UserException;

}
