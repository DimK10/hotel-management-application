package com.sphy.hotelmanagementapplication.exception;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sphy.hotelmanagementapplication.handler.RestError;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by dk
 */
@Component("customAuthenticationEntryPoint")
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	/**
	 * Method that acts as entry point for api authentication. If an error accuses, it
	 * resolves to unauthorized error http status
	 * @param request that resulted in an <code>AuthenticationException</code>
	 * @param response so that the user agent can begin authentication
	 * @param authException that caused the invocation
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {

		RestError re = new RestError(HttpStatus.UNAUTHORIZED.toString(), "Authentication failed");
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		OutputStream responseStream = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(responseStream, re);
		responseStream.flush();
	}
}