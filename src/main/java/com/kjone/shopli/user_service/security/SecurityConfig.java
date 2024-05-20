package com.kjone.shopli.user_service.security;


import com.kjone.shopli.user_service.security.cookie.CookieProvider;
import com.kjone.shopli.user_service.security.jwt.JwtAuthenticationFilter;
import com.kjone.shopli.user_service.security.jwt.JwtProvider;
import com.kjone.shopli.user_service.security.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtProvider jwtProvider;
    private final CustomUserDetailService customUserDetailService;
    private final CookieProvider cookieProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.POST, "/user/signup", "/user/signin").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/user/delete").permitAll()
                        .requestMatchers("/user/**").permitAll()
                        .anyRequest().denyAll());

        http.addFilterBefore(new JwtAuthenticationFilter(jwtProvider,cookieProvider, customUserDetailService),  UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
