package com.workintech.springecommerce.services.user;

import com.workintech.springecommerce.dto.AddressRequest;
import com.workintech.springecommerce.dto.AddressResponse;
import com.workintech.springecommerce.entity.user.User;

public interface UserService {
    User findById(Long id);
    User save(User user);

}
