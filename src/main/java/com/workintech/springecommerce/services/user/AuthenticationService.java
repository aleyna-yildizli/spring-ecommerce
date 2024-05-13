package com.workintech.springecommerce.services.user;

import com.workintech.springecommerce.entity.user.Role;
import com.workintech.springecommerce.entity.user.User;
import com.workintech.springecommerce.repository.RoleRepository;
import com.workintech.springecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public  User register(String fullName, String email, String password, Long roleId) {
        String encodedPassword = passwordEncoder.encode(password);
        Optional<Role> optionalRole = roleRepository.findById(roleId);

        Role userRole = new Role();
        if(optionalRole.isPresent()){
            userRole = roleRepository.findByCode(optionalRole.get().getCode()).orElseThrow();
        }

        User user = new User();
        user.setName(fullName);
        user.setEmail(email);
        user.setPassword(encodedPassword);

        Set<Role> roles = new HashSet<>();  // Kullanıcının rolleri
        roles.add(userRole); // Rol setine kullanıcının rolü eklenir
        user.setRoles(roles); // Kullanıcının rolleri User nesnesine atanır

        return userRepository.save(user);
    }
}









