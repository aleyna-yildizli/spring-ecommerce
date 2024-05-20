package com.workintech.springecommerce.services.user;

import com.workintech.springecommerce.DtoConverter.address.AddressConverter;
import com.workintech.springecommerce.dto.AddressRequest;
import com.workintech.springecommerce.dto.AddressResponse;
import com.workintech.springecommerce.entity.user.Address;
import com.workintech.springecommerce.entity.user.User;
import com.workintech.springecommerce.exceptions.EcommerceException;
import com.workintech.springecommerce.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private AddressService addressService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AddressService addressService) {
        this.userRepository = userRepository;
        this.addressService = addressService;
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() ->
                new EcommerceException("User is not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User verifyUser(Long userId) {
        return findById(userId);
    }
}
