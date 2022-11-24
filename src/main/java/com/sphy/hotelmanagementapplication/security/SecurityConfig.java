package com.sphy.hotelmanagementapplication.security;

import com.sphy.hotelmanagementapplication.Filter.JwtRequestFilter;
import com.sphy.hotelmanagementapplication.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/***
 * created by gp
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final UserService userService;

    private final Pbkdf2PasswordEncoder pbkdf2PasswordEncoder;

    private final JwtRequestFilter jwtRequestFilter;


    public SecurityConfig(UserService userService, Pbkdf2PasswordEncoder pbkdf2PasswordEncoder, JwtRequestFilter jwtRequestFilter) {
        this.userService = userService;
        this.pbkdf2PasswordEncoder = pbkdf2PasswordEncoder;
        this.jwtRequestFilter = jwtRequestFilter;
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(pbkdf2PasswordEncoder);

        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/api/login", "/api/signup").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
