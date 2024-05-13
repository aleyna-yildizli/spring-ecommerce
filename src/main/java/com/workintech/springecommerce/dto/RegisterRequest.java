package com.workintech.springecommerce.dto;

public record RegisterRequest(String name, String email, String password, Long role_id ) {
}
