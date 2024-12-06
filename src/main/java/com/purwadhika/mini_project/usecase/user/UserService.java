package com.purwadhika.mini_project.usecase.user;

import com.purwadhika.mini_project.entity.Roles;
import com.purwadhika.mini_project.entity.User;
import com.purwadhika.mini_project.infrastructure.users.model.RegisterModeratorRequest;
import com.purwadhika.mini_project.infrastructure.users.model.RegisterRequest;
import com.purwadhika.mini_project.infrastructure.users.repository.RoleRepository;
import com.purwadhika.mini_project.infrastructure.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    AuthenticationManager authenticationManager;

    private final UserRepository repository;
    private final RoleRepository roleRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserService(UserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    public User register(RegisterRequest user) {
        User newUser = user.toEntity();
        user.setPassword(encoder.encode(user.getPassword()));

        Roles role = roleRepository.getRolesByNameContainingIgnoreCase("USER").orElseThrow(() -> new RuntimeException("Role not found"));
        newUser.getRoles().add(role);
        return repository.save(newUser);
    }

    public User register(RegisterModeratorRequest user, Boolean isModerator) {
        User newUser = user.toEntity();
        user.setPassword(encoder.encode(user.getPassword()));

        Roles role = roleRepository.getRolesByNameContainingIgnoreCase("MODERATOR").orElseThrow(() -> new RuntimeException("Role not found"));
        newUser.getRoles().add(role);
        return repository.save(newUser);
    }

    public String verfiy(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
    if (authentication.isAuthenticated())
        return "Login successful";
    return "Invalid credentials";
    }
}
