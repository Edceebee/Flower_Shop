package com.flowerShop.api.repositories;

import com.flowerShop.api.models.User.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByPrimaryEmailAddress(String emailAddress);
}
