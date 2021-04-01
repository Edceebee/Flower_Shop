package com.flowerShop.web.controllers;

import com.flowerShop.api.controllers.UserController;
import com.flowerShop.api.controllers.dtos.APIResponse;
import com.flowerShop.api.controllers.dtos.user.UserDetailsRespDTO;
import com.flowerShop.api.controllers.dtos.user.UserRegReqDTO;
import com.flowerShop.api.models.User.UserCategory;
import com.flowerShop.api.models.User.UserLoginReqDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

@Controller
public class HomeController {

    UserController userController = new UserController();

    @GetMapping("/")
    public String renderLandingPage() {
        return "landing/landing";
    }

    @GetMapping("/register")
    public String renderRegistrationPage() {
        return "register/registration-page";
    }

    @GetMapping("/login")
    public String renderLoginPage() {
        return "login/login-page";
    }

    @PostMapping("/register")
    public String register(@RequestBody UserRegReqDTO userRegReqDTO, /*@ModelAttribute*/ Model model) {
        ResponseEntity<APIResponse> responseEntity = userController.register(userRegReqDTO); //api call

        if(responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
            model.addAttribute("errorMessage", Objects.requireNonNull(responseEntity.getBody()).getMessage());
            return "register/registration-page"; //with errorMessage
        }

        if(responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {

            //if buyer, re-render landing page with user logged in ...
            UserDetailsRespDTO userDetails = (UserDetailsRespDTO) Objects.requireNonNull(responseEntity.getBody()).getResponseDTO();
            model.addAttribute("userDetails", userDetails);

            setUserCategories(userDetails.getUserCategoryNumbers(), model);

            if(model.containsAttribute("isABuyer")) {
                return "landing/landing";
            }

            //if seller, render seller homepage
            if(model.containsAttribute("isASeller")) {
                return "seller/home";
            }
        }
        return "register/registration-page";
    }

    private void setUserCategories(char[] userCategoryNumbers, Model model) {
        int isABuyer = Arrays.binarySearch(userCategoryNumbers, UserCategory.BUYER.getFlag());
        if(isABuyer == 1) {
            model.addAttribute("isABuyer", true);
        }

        int isASeller = Arrays.binarySearch(userCategoryNumbers, UserCategory.SELLER.getFlag());
        if(isASeller == 1) {
            model.addAttribute("isASeller", true);
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginReqDTO userLoginReqDTO, /*@ModelAttribute*/ Model model) {
        ResponseEntity<APIResponse> responseEntity = userController.login(userLoginReqDTO); //api call

        if(responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
            model.addAttribute("errorMessage", Objects.requireNonNull(responseEntity.getBody()).getMessage());
            return "login/login-page"; //with errorMessage
        }

        if(responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            //if buyer, re-render landing page with user logged in ...
            UserDetailsRespDTO userDetails = (UserDetailsRespDTO) Objects.requireNonNull(responseEntity.getBody()).getResponseDTO();
            model.addAttribute("userDetails", userDetails);

            setUserCategories(userDetails.getUserCategoryNumbers(), model);

            if(model.containsAttribute("isABuyer")) {
                return "landing/landing";
            }

            //if seller, render seller homepage
            if(model.containsAttribute("isASeller")) {
                return "seller/home";
            }
        }

        return "login/login-page";
    }

}
