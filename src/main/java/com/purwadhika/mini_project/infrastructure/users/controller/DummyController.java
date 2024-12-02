package com.purwadhika.mini_project.infrastructure.users.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {
    @GetMapping("/")
    public String dummy(HttpServletRequest request) {
        return "Hello, World!" + request.getSession().getId();
    }
}
