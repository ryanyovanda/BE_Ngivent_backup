package com.purwadhika.mini_project.infrastructure.users.model;

import com.purwadhika.mini_project.entity.Roles;
import com.purwadhika.mini_project.entity.User;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private  String password;
    private String referral_code;

    public User toEntity() {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setReferralCode(referral_code);
        Set<Roles> roles = new HashSet<>();
        user.setRoles(roles);
        return user;
    }
}
