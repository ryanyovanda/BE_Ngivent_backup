package com.purwadhika.mini_project.infrastructure.users.controller;

import com.purwadhika.mini_project.entity.Dashboard;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DashboardController {

  private List<Dashboard> dashboards = new ArrayList<>(List.of(
          new Dashboard(
                  1,
                  "John Doe",
                  80
          ),
          new Dashboard(2,"sunny", 70)
  ));
   @GetMapping("/dashboard")
    public List<Dashboard> getDashboardData() {
    return dashboards;
   }

   @GetMapping("/csrf-token")
   public CsrfToken getCsrfToken(HttpServletRequest request) {
       return (CsrfToken) request.getAttribute("_csrf");
   }

   @PreAuthorize("hasAuthority('SCOPE_USER')")
   @PostMapping( "/dashboard")
    public Dashboard addDashboard(@RequestBody Dashboard dashboard) {
        dashboards.add(dashboard);
        return dashboard;
   }
}


