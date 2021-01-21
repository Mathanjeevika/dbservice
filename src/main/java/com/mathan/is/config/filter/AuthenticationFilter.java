package com.mathan.is.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mathan.is.util.CommonConstants;

@Configuration
public class AuthenticationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
			throws ServletException, IOException {
		String authorization = httpServletRequest.getHeader(CommonConstants.AUTH_HEADER);
		System.out.println(authorization);
		filterChain.doFilter(httpServletRequest, httpServletResponse);		
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return super.shouldNotFilter(request);
	}
	
}
