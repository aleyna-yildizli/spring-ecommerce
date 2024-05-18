package com.workintech.springecommerce.controller;

import com.workintech.springecommerce.dto.AddressRequest;
import com.workintech.springecommerce.dto.AddressResponse;
import com.workintech.springecommerce.entity.user.User;
import com.workintech.springecommerce.services.user.AddressService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/address")
@AllArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAllAddresses(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(401).body(null);
        }
        List<AddressResponse> addresses = addressService.getAllAddress(user);
        return ResponseEntity.ok(addresses);
    }

    @PostMapping
    public ResponseEntity<List<AddressResponse>> addAddress(@AuthenticationPrincipal User user, @RequestBody AddressRequest addressRequest) {
        if (user == null) {
            return ResponseEntity.status(401).body(null);
        }
        addressService.save(user, addressRequest);
        List<AddressResponse> addresses = addressService.getAllAddress(user);
        return ResponseEntity.status(200).body(addresses);
    }

}
