package com.flowerShop.api.services.User;

import com.flowerShop.api.controllers.dtos.user.UserRegReqDTO;
import com.flowerShop.api.controllers.dtos.user.UserRegRespDTO;
import com.flowerShop.exceptions.UserException;

public interface UserService {

    UserRegRespDTO register(UserRegReqDTO userRegDTO) throws UserException;

}
