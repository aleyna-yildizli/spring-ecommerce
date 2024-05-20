package com.workintech.springecommerce.services.user;

import com.workintech.springecommerce.DtoConverter.address.AddressConverter;
import com.workintech.springecommerce.dto.AddressRequest;
import com.workintech.springecommerce.dto.AddressResponse;
import com.workintech.springecommerce.entity.user.Address;
import com.workintech.springecommerce.entity.user.User;
import com.workintech.springecommerce.repository.user.AddressRepository;
import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    public  AddressRepository addressRepository;
    private  UserService userService;

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
        return user.getAddresses().stream()
                .map(address -> AddressConverter.toAddressResponse(address, user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Address save(User user, AddressRequest addressRequest) {
        Address address = AddressConverter.convertToAddressEntity(addressRequest);
        addressRepository.save(address);  // Önce adresi kaydediyoruz
        user.getAddresses().add(address);
        userService.save(user);  // Sonra kullanıcıyı kaydediyoruz
        return address;
    }

    @Override
    public AddressResponse update(User user, AddressRequest addressRequest) {
        Optional<Address> optionalAddress = addressRepository.findById(addressRequest.id());
        if (optionalAddress.isPresent() && user.getAddresses().contains(optionalAddress.get())) {
            Address address = optionalAddress.get();
            address.setName(addressRequest.name());
            address.setSurname(addressRequest.surname());
            address.setCity(addressRequest.city());
            address.setDistrict(addressRequest.district());
            address.setNeighborhood(addressRequest.neighborhood());
            address.setAddress(addressRequest.address());
            address.setTitle(addressRequest.title());
            address.setPhone(addressRequest.phone());

            Address updatedAddress = addressRepository.save(address);
            return AddressConverter.toAddressResponse(updatedAddress, user.getId());
        }
        return null;  // Or you can throw an exception if the address is not found or doesn't belong to the user
    }

    @Override
    public boolean delete(User user, Long addressId) {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        if (optionalAddress.isPresent() && user.getAddresses().contains(optionalAddress.get())) {
            Address address = optionalAddress.get();
            user.getAddresses().remove(address); // Kullanıcının adres listesinden çıkar
            userService.save(user);  // Kullanıcıyı güncelle ve kaydet
            addressRepository.delete(address); // Adresi sil
            return true;
        }
        return false;  // Ya da adres bulunamazsa bir istisna fırlatabilirsiniz
    }
}
