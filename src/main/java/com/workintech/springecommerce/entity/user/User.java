package com.workintech.springecommerce.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "Email can not be null")
    @NotEmpty(message = "Email can not be empty")
    @Size(min=10, message="Length of email can not be greater than 10 characters" )
    private String email;

    @Column(name="password")
    @NotNull(message = "Password can not be null")
    @NotEmpty(message = "Email can not be empty")
    @Size(min = 8, message = "Length of email can not be less than 8 characters")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER) //userla birlikte rolleri de getirmek için
    // Rollere göre yetkilendirmek için.
    @JoinTable(name="user_role", schema="ecommerce",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
