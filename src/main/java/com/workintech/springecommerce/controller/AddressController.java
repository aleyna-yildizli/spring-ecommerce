package com.workintech.springecommerce.controller;

import com.workintech.springecommerce.dto.AddressResponse;
import com.workintech.springecommerce.entity.user.Address;
import com.workintech.springecommerce.entity.user.User;

import com.workintech.springecommerce.services.user.AddressService;
import com.workintech.springecommerce.services.user.UserService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/address")
public class AddressController {
    private AddressService addressService;
    private UserService userService;

    public AddressController(AddressService addressService, UserService userService) {
        this.addressService = addressService;
        this.userService = userService;
    }

    @GetMapping
    public List<AddressResponse> getAllAddresses( User user) {
        return addressService.getAllAddress(user);
    }

    @PostMapping
    public ResponseEntity<Address> addAddress( User user, @RequestBody @Valid Address address) {
        Address savedAddress = addressService.save(user, address);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
    }

}
