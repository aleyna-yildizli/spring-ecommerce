package com.workintech.springecommerce.controller;
import com.workintech.springecommerce.dto.RoleResponse;
import com.workintech.springecommerce.services.user.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
@CrossOrigin("http://localhost:5173")
public class RoleController {

    private RoleService roleService;
    @GetMapping
    public List<RoleResponse> getAll(){
        return roleService.getAll();
    }
}
