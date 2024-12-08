package com.purwadhika.mini_project.infrastructure.users.controller;


import com.purwadhika.mini_project.entity.User;
import com.purwadhika.mini_project.infrastructure.users.model.RegisterModeratorRequest;
import com.purwadhika.mini_project.infrastructure.users.model.RegisterRequest;
import com.purwadhika.mini_project.usecase.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest user) {
        return service.register(user);
    }

    @PostMapping("/register/moderator")
    public User registerModerator(@RequestBody RegisterModeratorRequest user) {
        return service.register(user, true);
    }
}