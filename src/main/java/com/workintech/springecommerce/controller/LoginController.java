package com.workintech.springecommerce.controller;

import com.workintech.springecommerce.dto.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        // Burada login işlemini gerçekleştirin ve bir yanıt döndürün
        // Örneğin, başarılı giriş durumunda bir mesaj döndürebilirsiniz
        return "Login successful";
    }
}