package com.example.Newsletter_And_Email_Manager_Campain.Impl;


import com.example.Newsletter_And_Email_Manager_Campain.Model.Roles;
import com.example.Newsletter_And_Email_Manager_Campain.Model.User;
import com.example.Newsletter_And_Email_Manager_Campain.Repository.UserRepository;
import com.example.Newsletter_And_Email_Manager_Campain.Service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean createUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(List.of(Roles.USER));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean createAdminUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(List.of(Roles.ADMIN, Roles.USER));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByID(String id) {
        try {
            Optional<User> user = userRepository.findById(id);
            return user.orElse(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateUser(String id, User updateUser) {
        try {
            User userInDB = getUserByID(id);

            if (userInDB != null && updateUser != null) {
                if (updateUser.getName() != null && !updateUser.getName().isEmpty()) {
                    userInDB.setName(updateUser.getName());
                }

                if (updateUser.getEmail() != null && !updateUser.getEmail().isEmpty()) {
                    userInDB.setEmail(updateUser.getEmail());
                }

                if (updateUser.getPassword() != null && !updateUser.getPassword().isEmpty()) {
                    userInDB.setPassword(passwordEncoder.encode(updateUser.getPassword()));
                }

                if (updateUser.getRoles() != null && !updateUser.getRoles().isEmpty()) {
                    userInDB.setRoles(updateUser.getRoles());
                }

                userRepository.save(userInDB);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw  new RuntimeException("User can't be updated!!");
        }
    }

    @Override
    public boolean deleteUser(String id) {
        User user = getUserByID(id);
        if(user != null){
            userRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByName(username);
    }
}
