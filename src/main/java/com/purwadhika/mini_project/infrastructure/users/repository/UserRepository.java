package com.purwadhika.mini_project.infrastructure.users.repository;

import com.purwadhika.mini_project.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);


}
