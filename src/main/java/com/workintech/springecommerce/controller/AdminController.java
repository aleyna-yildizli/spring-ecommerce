package com.workintech.springecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AdminController {

    @GetMapping("/")
    public String admin(){
        return "This is admin area";
    }

}