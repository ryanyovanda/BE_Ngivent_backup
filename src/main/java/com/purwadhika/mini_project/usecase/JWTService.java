//package com.purwadhika.mini_project.usecase;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.KeyAgreementSpi;
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import java.security.Key;
//import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class JWTService {
//    private String secretKey = "your_secret_key_here"; // Replace with your actual secret key
//
//    public JWTService() {
//        try {
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
//            SecretKey sk = keyGenerator.generateKey();
//            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public String generateToken(String email) {
//        Map<String, Object> claims = new HashMap<>();
//        return Jwts.builder()
//                .setClaims()
//                .subject(email)
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiriesAt(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
//                .and()
//                .signWith(getkey())
//                .compact();
//
//        return "";
//    }
//
//    private Key getkey(){
//        byte[] keyBytes = Base64.Decoder.decode(secretKey);
//        return Keys.hmacShaKeyFor(secretKey);
//    }
//}
