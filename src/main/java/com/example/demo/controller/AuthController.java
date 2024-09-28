package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.dto.LoginDto;

@RestController
public class AuthController {

    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto request) {
        try {
            // DaoAuthenticationProviderを用いた認証を行う
            daoAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
            // JWTトークンの生成
            String token = JWT.create().withClaim("username",request.getUserName())
                    .sign(Algorithm.HMAC256("__secret__"));
            // トークンをクライアントに返す
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("x-auth-token",token);
            return new ResponseEntity(httpHeaders, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
