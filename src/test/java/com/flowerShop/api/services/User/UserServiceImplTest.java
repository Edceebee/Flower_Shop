package com.flowerShop.api.services.User;

import com.flowerShop.api.controllers.dtos.user.UserRegReqDTO;
import com.flowerShop.api.controllers.dtos.user.UserRegRespDTO;
import com.flowerShop.api.models.User.User;
import com.flowerShop.api.models.User.UserCategory;
import com.flowerShop.api.models.User.UserProfile;
import com.flowerShop.api.repositories.UserRepository;
import com.flowerShop.exceptions.UserException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    private UserRegReqDTO userRegReqDTO;
    private User realUser;
    private UserRegRespDTO userRegRespDTO;

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
                .userCategoryNumbers(new char[]{'1', '2'})
                .build();

        realUser = User
                .builder()
                .id("abc").primaryEmailAddress("ayodele@glow.com").tel("2347069544459")
                .passwordHash("h&w4h7%7fhd*jdj73").userCategories(new ArrayList<>()).profile(new UserProfile())
                .build();
        realUser.getUserCategories().add(UserCategory.BUYER);
        realUser.getUserCategories().add(UserCategory.SELLER);

        userRegRespDTO = UserRegRespDTO
                .builder()
                .emailAddress("ayodele@glow.com").tel("2347069544459").userCategoryNumbers(new char[]{'1', '2'})
                .build();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void register() {
        try {
            when(userRepository.save(any())).thenReturn(realUser);
            UserRegRespDTO returnedUserRegRespDTO = userService.register(userRegReqDTO);
            assertEquals(userRegRespDTO, returnedUserRegRespDTO);
        }
        catch(UserException userException) {
            System.err.println(userException.getMessage());
        }
    }
}
