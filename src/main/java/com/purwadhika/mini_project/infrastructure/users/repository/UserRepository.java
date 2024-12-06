package com.purwadhika.mini_project.infrastructure.users.repository;

import com.purwadhika.mini_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmailContainsIgnoreCase(String email);
    Optional<User> findByReferralCode(String referralCode);
//    User findByUsername(String username);
}
