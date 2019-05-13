package com.zuul.security.filters;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.zuul.util.SecurityConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtAutheticationFilter extends UsernamePasswordAuthenticationFilter {

	//@Autowired
	private AuthenticationManager authenticationManager;

	public JwtAutheticationFilter(AuthenticationManager authenticationManager) {
		 this.authenticationManager = authenticationManager;
		setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("---"+username);
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		setDetails(request, authenticationToken);
		return authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain, Authentication authentication) {System.out.println("success--auth");
		User user = ((User) authentication.getPrincipal());
System.out.println("*****"+user.getUsername());
		List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		// String signingKey = SecurityConstants.JWT_SECRET.getBytes();

		String token = Jwts.builder()
				// .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET)
				.setHeaderParam("typ", SecurityConstants.TOKEN_TYPE).setIssuer(SecurityConstants.JWT_TOKEN_ISSUER)
				.setAudience(SecurityConstants.JWT_TOKEN_AUDIENCE).setSubject(user.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + 864000000)).claim("rol", roles).compact();

		response.addHeader(SecurityConstants.JWT_TOKEN_HEADER, SecurityConstants.JWT_TOKEN_PREFIX + token);
	}

}
