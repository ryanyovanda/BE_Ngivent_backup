package com.purwadhika.mini_project.usecase.user;

import com.purwadhika.mini_project.entity.Users;
import com.purwadhika.mini_project.infrastructure.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Users register(Users user) {
        return repository.save(user);
    }

}
