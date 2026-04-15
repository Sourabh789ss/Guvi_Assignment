package com.example.Newsletter_And_Email_Manager_Campain.Service;

import com.example.Newsletter_And_Email_Manager_Campain.Model.User;

import java.util.List;

public interface UserService {

    boolean createUser(User user);

    boolean createAdminUser(User user);

    List<User> getAllUsers();

    User getUserByID(String id);

    boolean updateUser(String id, User user);

    boolean deleteUser(String id);

    User getUserByUsername(String username);
}