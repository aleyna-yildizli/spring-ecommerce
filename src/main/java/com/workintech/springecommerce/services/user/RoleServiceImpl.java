package com.workintech.springecommerce.services.user;

import com.workintech.springecommerce.DtoConverter.user.UserConverter;
import com.workintech.springecommerce.dto.RoleResponse;
import com.workintech.springecommerce.repository.user.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public List<RoleResponse> getAll() {
        return UserConverter.convertToRoleResponseList(roleRepository.findAll());
    }
}
