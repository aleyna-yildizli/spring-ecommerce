package com.workintech.springecommerce.controller;


import com.workintech.springecommerce.dto.LoginRequest;
import com.workintech.springecommerce.dto.LoginResponse;
import com.workintech.springecommerce.exceptions.EcommerceException;
import com.workintech.springecommerce.services.user.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
@Slf4j
public class LoginController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse response = authenticationService.authenticate(loginRequest.email(), loginRequest.password());
            log.info("User logged in successfully: {}", loginRequest.email());
            return ResponseEntity.ok(response);
        } catch (EcommerceException e) {
            log.error("Login failed for user: {}", loginRequest.email());
            return ResponseEntity.status(e.getHttpStatus()).body(null);
        }
    }
}
