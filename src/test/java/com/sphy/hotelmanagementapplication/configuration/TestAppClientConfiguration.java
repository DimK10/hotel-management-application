package com.sphy.hotelmanagementapplication.configuration;

import com.sphy.hotelmanagementapplication.domain.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;

import java.time.Instant;
import java.util.Map;

@TestConfiguration
@ConditionalOnMissingBean
public class TestAppClientConfiguration {

    @Bean
    public JwtDecoder jwtDecoder() {
        return new JwtDecoder() {
            @Override
            public Jwt decode(String token) throws JwtException {
                return new Jwt(
                        "token",
                        Instant.now(),
                        Instant.MAX,
                        Map.of("alg", "none"),
                        Map.of(
                                JwtClaimNames.SUB, "clientUser",
                                "role", User.Role.CLIENT
                        )
                );
            }
        };
    }
}
