package com.workintech.springecommerce.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user", schema = "ecommerce")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotBlank(message = "Name must not be null, empty or blank")
    @Size(min = 3, max = 30, message = "Name must not be less than 3 and greater than 30 characters.")
    @Column(name = "name")
    private String name;

    @Column(name="email")
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email must not be null, empty or blank")
    @Size(min=10, max=50, message="Length of email must be between 10 and 50 characters")
    private String email;


    @Column(name="password")
    @NotBlank(message = "Password must not be null, empty or blank")
    @Size(min = 8, max=100, message = "Length of password must be between 8 and 100 characters")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role", schema="ecommerce",    // Rollere göre yetkilendirmek için
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles = new HashSet<>();


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name="user_address", schema="ecommerce",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="address_id"))
    private Set<Address> addresses = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
