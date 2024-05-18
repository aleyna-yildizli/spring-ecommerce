package com.workintech.springecommerce.controller;

import com.workintech.springecommerce.dto.RegisterRequest;
import com.workintech.springecommerce.dto.RegisterResponse;
import com.workintech.springecommerce.entity.user.User;
import com.workintech.springecommerce.services.user.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
@AllArgsConstructor
public class RegisterController {
    private AuthenticationService authenticationService;
    @PostMapping()
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
       User createdUser =  authenticationService
                .register(registerRequest.name(), registerRequest.email(), registerRequest.password(), registerRequest.role_id());
        return new RegisterResponse(createdUser.getEmail(), "Registration successful.");
    }
}
