package com.workintech.springecommerce.services.user;

import com.workintech.springecommerce.dto.AddressRequest;
import com.workintech.springecommerce.dto.AddressResponse;
import com.workintech.springecommerce.entity.user.Address;
import com.workintech.springecommerce.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Optional<Address> findById(Long id);

    List<AddressResponse> getAllAddress(User user);

    Address save(User user, AddressRequest addressRequest);

}
