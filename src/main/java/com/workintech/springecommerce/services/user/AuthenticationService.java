package com.workintech.springecommerce.services.user;


import com.workintech.springecommerce.dto.LoginResponse;
import com.workintech.springecommerce.entity.user.Role;
import com.workintech.springecommerce.entity.user.User;
import com.workintech.springecommerce.exceptions.EcommerceException;
import com.workintech.springecommerce.repository.user.RoleRepository;
import com.workintech.springecommerce.repository.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

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

    public LoginResponse authenticate(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        System.out.println("Authenticated user: " + authentication.getPrincipal());

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userRepository.findUserByEmail(email).orElseThrow(() ->
                    new EcommerceException("User not found", HttpStatus.NOT_FOUND));


            Role userRole = user.getRoles().iterator().next(); // Kullanıcının ilk rolünü al
            return new LoginResponse(user.getEmail(), user.getName(), userRole.getId().toString());
        } else {
            throw new EcommerceException("Invalid login attempt", HttpStatus.UNAUTHORIZED);
        }
    }
}