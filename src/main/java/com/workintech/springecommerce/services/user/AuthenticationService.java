package com.workintech.springecommerce.services.user;

import com.workintech.springecommerce.DtoConverter.user.UserConverter;
import com.workintech.springecommerce.dto.LoginResponse;
import com.workintech.springecommerce.entity.user.Role;
import com.workintech.springecommerce.entity.user.User;
import com.workintech.springecommerce.repository.user.RoleRepository;
import com.workintech.springecommerce.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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

    public LoginResponse login(String email, String password) {
        // Kullanıcıyı e-posta adresine göre bul
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Kullanıcının parolasını doğrula
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        // Kullanıcının bilgilerini döndür
        return UserConverter.convertToUserResponse(user);
    }
}





