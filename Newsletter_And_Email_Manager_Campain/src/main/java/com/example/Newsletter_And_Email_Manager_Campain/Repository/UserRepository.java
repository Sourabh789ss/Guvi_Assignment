package com.example.Newsletter_And_Email_Manager_Campain.Repository;

import com.example.Newsletter_And_Email_Manager_Campain.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByName(String username);

    User findByEmailIgnoreCase(String email);
}
