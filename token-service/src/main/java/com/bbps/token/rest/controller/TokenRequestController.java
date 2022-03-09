package com.bbps.token.rest.controller;

import com.bbps.token.rest.model.JwtRequest;
import com.bbps.token.rest.model.JwtResponse;
import com.bbps.token.rest.model.TokenUser;
import com.bbps.token.rest.repository.UserRepository;
import com.bbps.token.rest.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
public class TokenRequestController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/get-token")
    public ResponseEntity<JwtResponse> getToken(@RequestBody JwtRequest jwtRequest) {
        TokenUser user = userRepository.findByUsernameAndPassword(jwtRequest.getUsername(), generateHash(jwtRequest.getPassword()));
        JwtResponse response = new JwtResponse(null, null);
        String token = null;
        if(user != null) {
            token = this.jwtUtil.generateToken(user);
            response.setToken(token);
            response.setExpiry(jwtUtil.extractExpiration(token));
        }
        return ResponseEntity.ok(response);
    }

    private String generateHash(String input) {
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] b = md.digest(input.getBytes(StandardCharsets.UTF_8));
            hexString = new StringBuilder(new BigInteger(1, b).toString(16));
            while(hexString.length() < 32) {
                hexString.insert(0, '0');
            }
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }
}
