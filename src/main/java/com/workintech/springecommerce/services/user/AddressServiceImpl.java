package com.workintech.springecommerce.services.user;

import com.workintech.springecommerce.DtoConverter.address.AddressConverter;
import com.workintech.springecommerce.dto.AddressResponse;
import com.workintech.springecommerce.entity.user.Address;
import com.workintech.springecommerce.entity.user.User;
import com.workintech.springecommerce.exceptions.EcommerceException;
import com.workintech.springecommerce.repository.user.AddressRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    public AddressRepository addressRepository;
    private UserService userService;

    public AddressServiceImpl(AddressRepository addressRepository, @Lazy UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }


    @Override
    public List<AddressResponse> getAllAddress(User user) {
        List<Address> addresses = new ArrayList<>(user.getAddresses());
        return AddressConverter.toAddressResponseList(user, addresses);
    }

    @Override
    public Address save(User user, Address address) {
        user.getAddresses().add(address);
        userService.save(user);
        return addressRepository.save(address);
    }
}
