package com.workintech.springecommerce.dto;

import com.workintech.springecommerce.entity.user.Address;

public record AddressResponse (Long user_id, Address address ){
}
