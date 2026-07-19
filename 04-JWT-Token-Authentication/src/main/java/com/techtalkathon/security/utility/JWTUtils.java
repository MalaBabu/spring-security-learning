package com.techtalkathon.security.utility;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtils {
	private final long EXPIRATION_TIME = 1000 * 60 * 60;
	private final String SECRETE = "my-super-key-that-is-long-enough-123456789-!@#";
	private final SecretKey key = Keys.hmacShaKeyFor(SECRETE.getBytes());

	public String generateToken(String username) {
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();

	}

	
	public String extractUserName(String token) {
		Claims body = extractClaims(token);

		return body.getSubject();
	}


	private Claims extractClaims(String token) {
		return Jwts.parser()
				.setSigningKey(key).build()
				.parseClaimsJws(token)
				.getBody();
	}


	public boolean validateToken(String username, UserDetails userByUsername, String token) {
		// TODO Check if Username is same as in User Details .

		// TODO check if token is not expired
		return username.equals(userByUsername.getUsername()) && isTokenExpired(token);

	}

	private boolean isTokenExpired(String token) {
		Date expiration = extractClaims(token).getExpiration();

		boolean flag = new Date().before(expiration);

		return flag;

	}
}
