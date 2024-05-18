package com.workintech.springecommerce.controller;


import com.workintech.springecommerce.dto.LoginRequest;
import com.workintech.springecommerce.dto.LoginResponse;
import com.workintech.springecommerce.services.user.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        LoginResponse loginResponse = authenticationService.authenticate(loginRequest.email(), loginRequest.password());
        session.setAttribute("user", loginResponse);
        return ResponseEntity.ok(loginResponse);
    }
}
