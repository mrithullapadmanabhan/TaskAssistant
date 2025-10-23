package com.project.taskassistant.controller;

import com.project.taskassistant.model.User;
import com.project.taskassistant.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/id/{id}")
    public Optional<User> getUserById(@PathVariable String id){
        return Optional.ofNullable(userService.getUserById(id));
    }

    @GetMapping("/email/{emailId}")
    public Optional<User> getUserByEmail(@PathVariable String emailId){
        return Optional.ofNullable(userService.getUserByEmail(emailId));
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable String id){
        userService.deleteUserById(id);
    }
}
