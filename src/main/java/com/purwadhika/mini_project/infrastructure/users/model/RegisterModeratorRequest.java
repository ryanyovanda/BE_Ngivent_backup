package com.purwadhika.mini_project.infrastructure.users.model;

import com.purwadhika.mini_project.entity.Roles;
import com.purwadhika.mini_project.entity.User;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RegisterModeratorRequest {
    private String username;
    private String email;
    private  String password;
//    private String referralCode;

    public User toEntity() {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
//        user.setReferralCode(referralCode);
        Set<Roles> roles = new HashSet<>();
        user.setRoles(roles);
        return user;
    }
}
