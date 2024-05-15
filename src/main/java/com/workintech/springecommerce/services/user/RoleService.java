package com.workintech.springecommerce.services.user;

import com.workintech.springecommerce.dto.RoleResponse;

import java.util.List;

public interface RoleService {
    List<RoleResponse> getAll();
}
