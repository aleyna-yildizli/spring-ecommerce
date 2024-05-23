package com.workintech.springecommerce.controller;

import com.workintech.springecommerce.dto.AddressRequest;
import com.workintech.springecommerce.dto.AddressResponse;
import com.workintech.springecommerce.entity.user.Address;
import com.workintech.springecommerce.entity.user.User;
import com.workintech.springecommerce.exceptions.EcommerceException;
import com.workintech.springecommerce.services.user.AddressService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

    private void verifyUser(User user) {
        if (user == null) {
            throw new EcommerceException("User is not authenticated", HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAllAddresses(@AuthenticationPrincipal User user) {
        verifyUser(user);
        System.out.println("Authenticated User: " + user.getEmail());
        List<AddressResponse> addresses = addressService.getAllAddress(user);
        return ResponseEntity.ok(addresses);
    }

    @PostMapping
    public ResponseEntity<List<AddressResponse>> addAddress(@AuthenticationPrincipal User user, @RequestBody AddressRequest addressRequest) {
        verifyUser(user);
        addressService.save(user, addressRequest);
        List<AddressResponse> addresses = addressService.getAllAddress(user);
        return ResponseEntity.status(200).body(addresses);
    }
    @PutMapping
    public ResponseEntity<AddressResponse> updateAddress(@AuthenticationPrincipal User user, @RequestBody AddressRequest addressRequest) {
        verifyUser(user);
        AddressResponse updatedAddress = addressService.update(user, addressRequest);
        if (updatedAddress != null) {
            return ResponseEntity.ok(updatedAddress);
        } else {
            throw new EcommerceException("Address not found or doesn't belong to the user", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@AuthenticationPrincipal User user, @PathVariable Long id) {
        verifyUser(user);
        Optional<Address> address = addressService.findById(id);
        if (address.isEmpty() || !user.getAddresses().contains(address.get())) {
            throw new EcommerceException("Address not found or doesn't belong to the user", HttpStatus.NOT_FOUND);
        }
        boolean isDeleted = addressService.delete(user, id);
        if (isDeleted) {
            return ResponseEntity.status(204).build(); // HTTP 204 No Content
        } else {
            throw new EcommerceException("Failed to delete the address", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

//kullanıcı kimliğini doğrulamak için -> @AuthenticationPrincipal