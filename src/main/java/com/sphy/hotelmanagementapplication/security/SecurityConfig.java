package com.sphy.hotelmanagementapplication.security;

import com.sphy.hotelmanagementapplication.Filter.JwtRequestFilter;
import com.sphy.hotelmanagementapplication.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/***
 * created by gp
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = false, securedEnabled = false, jsr250Enabled = true
)
public class SecurityConfig {

	private final UserService userService;

	private Pbkdf2PasswordEncoder pbkdf2PasswordEncoder;

	private final JwtRequestFilter jwtRequestFilter;


	private final AuthenticationEntryPoint authEntryPoint;

	public SecurityConfig(UserService userService, JwtRequestFilter jwtRequestFilter,
			@Qualifier("customAuthenticationEntryPoint") AuthenticationEntryPoint authEntryPoint) {
		this.userService = userService;
		this.jwtRequestFilter = jwtRequestFilter;
		this.authEntryPoint = authEntryPoint;
	}


	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {

		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

		authenticationProvider.setUserDetailsService(userService);
		pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
		authenticationProvider.setPasswordEncoder(pbkdf2PasswordEncoder);

		return authenticationProvider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/signup").permitAll()
				.antMatchers(HttpMethod.POST, "/api/login").permitAll()
				.anyRequest().authenticated()
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(authEntryPoint);


		http
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
