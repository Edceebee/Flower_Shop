package com.flowerShop.api.services.User;

import com.flowerShop.api.models.User.User;
import com.flowerShop.api.controllers.dtos.user.UserRegReqDTO;
import com.flowerShop.api.controllers.dtos.user.UserDetailsRespDTO;
import com.flowerShop.api.models.User.UserLoginReqDTO;
import com.flowerShop.api.models.User.UserProfile;
import com.flowerShop.api.repositories.UserRepository;
import com.flowerShop.api.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetailsRespDTO register(UserRegReqDTO userRegDTO) throws UserException {
        if(userRegDTO == null) {
            throw new UserException("User Registration Information not Supplied");
        }

        // TODO: 3/28/21 a validation method that ensures that each required field is present

        String username = userRegDTO.getUsername();
        if(username != null && !(username.isEmpty())) {
            if(usernameExists(username)) {
                throw new UserException("Username already taken");
            }
        }

        String emailAddress = userRegDTO.getEmailAddress();
        if(!(emailAddress.isEmpty())) {
            if(emailAddressExists(emailAddress)) {
                throw new UserException("Email address matches an existing account");
            }
        }

        User newUser = User
                .builder()
                .username(userRegDTO.getUsername()).primaryEmailAddress(userRegDTO.getEmailAddress())
                .tel(userRegDTO.getTel()).passwordHash(User.hashPassword(userRegDTO.getPassword())).userCategories(new ArrayList<>())
                .profile(new UserProfile()).createdOn(LocalDateTime.now())
                .build();

        newUser.addCategories(userRegDTO.getUserCategoryNumbers());

        return save(newUser);
    }

    private boolean emailAddressExists(String emailAddress) {
        Optional<User> foundUser = userRepository.findUserByPrimaryEmailAddress(emailAddress);
        return foundUser.isPresent();
    }

    private boolean usernameExists(String username) {
        Optional<User> foundUser = userRepository.findUserByUsername(username);
        return foundUser.isPresent();
    }

    @Override
    public UserDetailsRespDTO login(UserLoginReqDTO userLoginReqDTO) throws UserException {
        if(userLoginReqDTO == null) {
            throw new UserException("User Login Information not Supplied");
        }

        Optional<User> foundUserOpt = userRepository.findUserByUsername(userLoginReqDTO.getUsername());

        if(foundUserOpt.isEmpty()){
            foundUserOpt = userRepository.findUserByPrimaryEmailAddress(userLoginReqDTO.getEmailAddress());
        }

        if(foundUserOpt.isPresent()) {
            User foundUser = foundUserOpt.get();

            if(User.hashPassword(userLoginReqDTO.getPassword())
                    .equals(foundUser.getPasswordHash())) {

                return serializeUserToDTO(foundUser);
            }
            else {
                throw new UserException("Incorrect Password Supplied");
            }
        }
        else {
            throw new UserException("Incorrect Username or Email Address");
        }
    }

    private UserDetailsRespDTO save(User user) {
        User savedUser = userRepository.save(user);
        return serializeUserToDTO(savedUser);
    }

    private UserDetailsRespDTO serializeUserToDTO(User user) {

        char[] userCategoryNumbers = user.getUserCategoryNumbers();

        return UserDetailsRespDTO
                .builder()
                .username(user.getUsername()).emailAddress(user.getPrimaryEmailAddress())
                .tel(user.getTel()).userCategoryNumbers(userCategoryNumbers)
                .firstName(user.getProfile().getFirstName()).lastName(user.getProfile().getLastName())
                .middleName(user.getProfile().getMiddleName())
                .dateOfBirth(user.getProfile().getDateOfBirth())
                .addresses(user.getProfile().getAddresses())
                .build();
    }
}
