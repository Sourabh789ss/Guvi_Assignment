package com.example.Newsletter_And_Email_Manager_Campain.Controller;

import com.example.Newsletter_And_Email_Manager_Campain.Model.User;
import com.example.Newsletter_And_Email_Manager_Campain.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get current user
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(
                userService.getUserByUsername(username)
        );
    }

    // Update user
    @PutMapping("/me")
    public ResponseEntity<?> updateUser(
            @RequestBody User user,
            Authentication authentication) {

        String username = authentication.getName();

        User user1 = userService.getUserByUsername(username);

        return ResponseEntity.ok(
                userService.updateUser(user1.getId(), user)
        );
    }

    // Delete user
    @DeleteMapping("/me")
    public ResponseEntity<?> deleteUser(Authentication authentication) {
        String username = authentication.getName();

        userService.deleteUser(username);
        return ResponseEntity.ok("User deleted successfully");
    }
}
