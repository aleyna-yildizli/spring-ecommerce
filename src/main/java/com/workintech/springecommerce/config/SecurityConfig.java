package com.workintech.springecommerce.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import java.util.Arrays;
import java.util.List;

//authentication-> login
//authorization-> yetkilendirme-rol based
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){ //parolayı encode etmek için
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(UserDetailsService userDetailsService){ //securityi databaseden yapmak için
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().configurationSource(corsConfigurationSource());
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/signup/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/roles/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST,"/login/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/categories/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/products/**").permitAll();
                    auth.requestMatchers("/css/**", "/js/**", "/images/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin(formLogin -> formLogin
                        .loginPage("/login") // Özel login sayfasını belirt
                        .permitAll() // Herkese login sayfasına erişim izni ver
                        .loginProcessingUrl("http://localhost:5173/login") // Giriş işlemini gerçekleştirecek URL
                        .defaultSuccessUrl("/", true) // Başarılı giriş sonrası yönlendirilecek URL
                        .failureUrl("/") // Hatalı giriş sonrası yönlendirilecek URL
                        .usernameParameter("email") // Kullanıcı adı parametresi
                        .passwordParameter("password") // Parola parametresi
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
