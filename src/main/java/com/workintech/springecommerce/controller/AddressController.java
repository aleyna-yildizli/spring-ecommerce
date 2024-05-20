package com.workintech.springecommerce.controller;

import com.workintech.springecommerce.dto.AddressRequest;
import com.workintech.springecommerce.dto.AddressResponse;
import com.workintech.springecommerce.entity.user.Address;
import com.workintech.springecommerce.entity.user.User;
import com.workintech.springecommerce.services.user.AddressService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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


    @PutMapping
    public ResponseEntity<AddressResponse> updateAddress(@AuthenticationPrincipal User user, @RequestBody AddressRequest addressRequest) {
        if (user == null) {
            return ResponseEntity.status(401).body(null);
        }
        AddressResponse updatedAddress = addressService.update(user, addressRequest);
        if (updatedAddress != null) {
            return ResponseEntity.ok(updatedAddress);
        } else {
            return ResponseEntity.status(404).body(null);  // Or appropriate status code if the address is not found or doesn't belong to the user
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@AuthenticationPrincipal User user, @PathVariable Long id) {
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        Optional<Address> address = addressService.findById(id);
        if (address.isEmpty() || !user.getAddresses().contains(address.get())) {
            return ResponseEntity.status(404).build();
        }
        boolean isDeleted = addressService.delete(user, id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(500).build();
        }
    }

}
