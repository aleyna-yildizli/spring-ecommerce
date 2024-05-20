package com.workintech.springecommerce.dto;

import com.workintech.springecommerce.entity.user.Address;

public record AddressRequest(
        Long id,
        Long user_id,
        String name,
        String surname,
        String city,
        String district,
        String neighborhood,
        String address,
        String title,
        String phone
) {
}
