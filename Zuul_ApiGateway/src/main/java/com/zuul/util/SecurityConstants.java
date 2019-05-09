package com.zuul.util;

public class SecurityConstants {
	
	public static final String AUTH_LOGIN_URL = "/api/authenticate";
	
    public static final String TOKEN_TYPE = "JWT";
	public static final String JWT_SECRET="secret";
	public static final Long JWT_EXPIRATION=84000l;
	public static final String JWT_TOKEN_PREFIX="Bearer ";
	public static final String JWT_TOKEN_HEADER="Authorization";
	public static final String JWT_TOKEN_ISSUER="prasad";
	public static final String JWT_TOKEN_AUDIENCE="people";
	
	
}
