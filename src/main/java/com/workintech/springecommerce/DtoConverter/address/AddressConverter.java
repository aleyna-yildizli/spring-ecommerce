package com.workintech.springecommerce.DtoConverter.address;

import com.workintech.springecommerce.dto.AddressResponse;
import com.workintech.springecommerce.entity.user.Address;
import com.workintech.springecommerce.entity.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddressConverter {
    public static AddressResponse toAddressResponse(User user, Address address) {
        return new AddressResponse(user.getId(), address);
    }

    public static List<AddressResponse> toAddressResponseList(User user, List<Address> addresses) {
        List<AddressResponse> addressResponses = new ArrayList<>();
        for (Address address : addresses) {
            addressResponses.add(new AddressResponse(user.getId(), address));
        }
        return addressResponses;
    }
}
