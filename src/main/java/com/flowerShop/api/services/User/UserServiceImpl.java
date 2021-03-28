package com.flowerShop.api.services.User;

import com.flowerShop.api.models.User.User;
import com.flowerShop.api.controllers.dtos.user.UserRegReqDTO;
import com.flowerShop.api.controllers.dtos.user.UserRegRespDTO;
import com.flowerShop.api.models.User.UserProfile;
import com.flowerShop.api.repositories.UserRepository;
import com.flowerShop.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserRegRespDTO register(UserRegReqDTO userRegDTO) throws UserException {
        if(userRegDTO == null) {
            throw new UserException("User Registration Information not Supplied");
        }

        // TODO: 3/28/21 a validation method that ensures that each required field is present
        // lombok @NonNull already took care of this, great!

        User newUser = User
                .builder()
                .username(userRegDTO.getUsername()).primaryEmailAddress(userRegDTO.getEmailAddress())
                .tel(userRegDTO.getTel()).passwordHash(userRegDTO.getPassword()).userCategories(new ArrayList<>())
                .profile(new UserProfile())
                .build();

        newUser.addCategories(userRegDTO.getUserCategoryNumbers());

        return save(newUser);
    }

    private UserRegRespDTO save(User user) {
        User savedUser = userRepository.save(user);

        char[] userCategoryNumbers = savedUser.getUserCategoryNumbers();

        return UserRegRespDTO
                .builder()
                .username(savedUser.getUsername()).emailAddress(savedUser.getPrimaryEmailAddress())
                .tel(savedUser.getTel()).userCategoryNumbers(userCategoryNumbers)
                .firstName(savedUser.getProfile().getFirstName()).lastName(savedUser.getProfile().getLastName())
                .middleName(savedUser.getProfile().getMiddleName())
                .dateOfBirth(savedUser.getProfile().getDateOfBirth())
                .addresses(savedUser.getProfile().getAddresses())
                .build();
    }
}
