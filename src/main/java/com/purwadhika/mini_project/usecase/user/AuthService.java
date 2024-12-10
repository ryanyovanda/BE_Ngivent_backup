//package com.purwadhika.mini_project.usecase.user;
//
//import com.GrAsp.EventureBackend.dto.LoginRequest;
//import com.GrAsp.EventureBackend.dto.LoginResponse;
//import com.GrAsp.EventureBackend.dto.LogoutRequest;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthService {
//    private final AuthenticationManager authenticationManager;
//    private final TokenService tokenService;
//    private final JwtDecoder jwtDecoder;
//    private final long ACCESS_TOKEN_EXPIRY = 900L;
//    private final long REFRESH_TOKEN_EXPIRY = 86400L;
//
//    public AuthService(AuthenticationManager authenticationManager, TokenService tokenService, JwtDecoder jwtDecoder) {
//        this.authenticationManager = authenticationManager;
//        this.tokenService = tokenService;
//        this.jwtDecoder = jwtDecoder;
//    }
//
//    public LoginResponse authenticateUser(LoginRequest req) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
//            );
//            String accessToken = tokenService.generateToken(authentication, TokenService.TokenType.ACCESS);
//            String refreshToken = tokenService.generateToken(authentication, TokenService.TokenType.REFRESH);
//            return new LoginResponse(accessToken, refreshToken, "Bearer");
//        } catch (AuthenticationException e) {
//            throw new RuntimeException("Wrong credentials");
//        }
//    }
//
//    public boolean checkPassword(String email, String password) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(email, password)
//            );
//            return authentication.isAuthenticated();
//        } catch (AuthenticationException e) {
//            throw new RuntimeException("Wrong password");
//        }
//    }
//
//    public Boolean logoutUser(LogoutRequest req) {
//        // Since we're not blacklisting tokens, this method can be simplified or removed.
//        // If you need to perform any other logout-related operations, do them here.
//        return Boolean.TRUE;
//    }
//}
