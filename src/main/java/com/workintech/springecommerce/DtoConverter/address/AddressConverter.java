package com.workintech.springecommerce.DtoConverter.address;

import com.workintech.springecommerce.dto.AddressRequest;
import com.workintech.springecommerce.dto.AddressResponse;
import com.workintech.springecommerce.entity.user.Address;

public class AddressConverter {

    public static AddressResponse toAddressResponse(Address address, Long user_id) {
        return new AddressResponse(
                address.getId(),
                user_id,
                address.getName(),
                address.getSurname(),
                address.getCity(),
                address.getDistrict(),
                address.getNeighborhood(),
                address.getAddress(),
                address.getTitle(),
                address.getPhone()
        );
    }

    public static Address convertToAddressEntity(AddressRequest addressRequest) {
        return new Address(
                addressRequest.id(),
                addressRequest.name(),
                addressRequest.surname(),
                addressRequest.city(),
                addressRequest.district(),
                addressRequest.neighborhood(),
                addressRequest.address(),
                addressRequest.title(),
                addressRequest.phone()
        );
    }
}
