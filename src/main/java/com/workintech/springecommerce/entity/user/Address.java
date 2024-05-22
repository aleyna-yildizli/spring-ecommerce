package com.workintech.springecommerce.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "address", schema = "ecommerce")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotBlank(message = "Name must not be null, empty or blank")
    @Size(min = 3, max = 50, message = "Length of name must be between 3 and 50 characters")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Surname must not be null, empty or blank")
    @Size(min = 2, max = 50, message = "Length of surname must be between 2 and 50 characters")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "City must not be null, empty or blank")
    @Size(min = 3, max = 50, message = "City must be between 3 and 50 characters")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "District must not be null, empty or blank")
    @Size(min = 3, max = 30, message = "District must be between 3 and 30 characters")
    @Column(name = "district")
    private String district;

    @NotBlank(message = "Neighborhood must not be null, empty or blank")
    @Size(min = 3, max = 30, message = "Neighborhood must be between 3 and 30 characters")
    @Column(name = "neighborhood")
    private String neighborhood;


    @Size(min = 3, max = 500, message = "Address must be between 3 and 300 characters")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "Title must not be null, empty or blank")
    @Size(min = 2, max = 30, message = "Title must be between 3 and 50 characters")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Phone must not be null, empty or blank")
    @Size(min = 9, max = 20, message = "Phone must be between 3 and 10 characters")
    @Column(name = "phone")
    private String phone;


}
