package com.flowerShop.api.services.User;

import com.flowerShop.api.controllers.dtos.user.UserRegReqDTO;
import com.flowerShop.api.controllers.dtos.user.UserDetailsRespDTO;
import com.flowerShop.api.models.User.User;
import com.flowerShop.api.models.User.UserCategory;
import com.flowerShop.api.models.User.UserLoginReqDTO;
import com.flowerShop.api.models.User.UserProfile;
import com.flowerShop.api.repositories.UserRepository;
import com.flowerShop.api.exceptions.UserException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    private UserRegReqDTO userRegReqDTO;
    private User realUser;
    private UserDetailsRespDTO userDetailsRespDTO;
    private UserLoginReqDTO userLoginReqDTO;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        userRegReqDTO = UserRegReqDTO
                .builder()
                .emailAddress("ayodele@glow.com").tel("2347069544459").password("password")
                .userCategoryNumbers(new char[]{
                        UserCategory.BUYER.getFlag(), UserCategory.SELLER.getFlag()
                })
                .build();

        realUser = User
                .builder()
                .id("abc").primaryEmailAddress("ayodele@glow.com").tel("2347069544459")
                .passwordHash(User.hashPassword("password")).userCategories(new ArrayList<>()).profile(new UserProfile())
                .createdOn(LocalDateTime.now())
                .build();
        realUser.getUserCategories().add(UserCategory.BUYER);
        realUser.getUserCategories().add(UserCategory.SELLER);

        userDetailsRespDTO = UserDetailsRespDTO
                .builder()
                .emailAddress("ayodele@glow.com").tel("2347069544459").userCategoryNumbers(new char[]{
                        UserCategory.BUYER.getFlag(), UserCategory.SELLER.getFlag()
                })
                .build();

        userLoginReqDTO = UserLoginReqDTO
                .builder()
                .emailAddress("ayodele@glow.com")
                .password("password")
                .build();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void register() {
        try {
            when(userRepository.save(any())).thenReturn(realUser);
            UserDetailsRespDTO returnedUserDetailsRespDTO = userService.register(userRegReqDTO);
            assertEquals(userDetailsRespDTO, returnedUserDetailsRespDTO);
        }
        catch(UserException userException) {
            System.err.println(userException.getMessage());
        }
    }

    @Test
    void registerWithNullDTO_throwsUserException() {
        assertThrows(UserException.class, ()-> userService.register(null));
    }

    @Test
    void login() {
        try {
            when(userRepository.findUserByPrimaryEmailAddress(any()))
                    .thenReturn(java.util.Optional.ofNullable(realUser));
            UserDetailsRespDTO returnedUserDetailsRespDTO = userService.login(userLoginReqDTO);
            assertEquals(userDetailsRespDTO, returnedUserDetailsRespDTO);
        }
        catch(UserException userException) {
            System.err.println(userException.getMessage());
        }
    }

    @Test
    void loginWithNullDTO_throwsUserException() {
        assertThrows(UserException.class, ()-> userService.login(null));
    }
}
