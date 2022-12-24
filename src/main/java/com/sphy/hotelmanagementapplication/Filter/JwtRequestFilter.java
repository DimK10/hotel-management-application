package com.sphy.hotelmanagementapplication.Filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sphy.hotelmanagementapplication.handler.RestError;
import com.sphy.hotelmanagementapplication.security.JwtUtil;
import com.sphy.hotelmanagementapplication.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/***
 * created by gp
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserService userService;

    private final JwtUtil jwtUtil;

    private final HandlerExceptionResolver exceptionResolver;

    public JwtRequestFilter(UserService userService, JwtUtil jwtUtil, @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String userName = null;

        String jwt = null;

        try {

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {

                jwt = authorizationHeader.substring(7);
                userName = jwtUtil.extractUsername(jwt);


                if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    UserDetails userDetails = this.userService.loadUserByUsername(userName);

                    if (jwtUtil.validateToken(jwt, userDetails)) {

                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                        usernamePasswordAuthenticationToken
                                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {

            log.error("Jwt Filter exception: ", e);

            // TODO MOVE TO METHOD - DUPLICATE LINES
            RestError re = new RestError(HttpStatus.UNAUTHORIZED.toString(), "Authentication failed");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            OutputStream responseStream = response.getOutputStream();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(responseStream, re);
            responseStream.flush();
            exceptionResolver.resolveException(request, response, null, e);
        }


    }

}
