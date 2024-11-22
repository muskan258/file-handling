package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private List<User> users = new ArrayList<>();

    // Create a new user (POST)
    @PostMapping
    public User createUser(@RequestBody User user) {
        // Avoid adding a user with an existing ID
        users.removeIf(existingUser -> existingUser.getId().equals(user.getId()));
        users.add(user);
        return user;
    }

    // Get all users (GET)
    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    // Get a user by ID (GET)
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    // Update a user (PUT)
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                user.setName(updatedUser.getName());
                user.setLocation(updatedUser.getLocation());
                return user;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        boolean removed = users.removeIf(user -> user.getId().equals(id));
        if (removed) {
            return "User with ID " + id + " deleted.";
        } else {
            return "User with ID " + id + " not found.";
        }
    }

}
