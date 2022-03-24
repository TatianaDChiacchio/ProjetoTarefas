package com.robo.gerenciadorTarefas.security.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	static final String TAREFA_USERNAME = "sub";
	static final String TAREFA_CREATED = "created";
	static final String TAREFA_EXPIRED = "exp";
	
	public static final int TOKEN_EXPIRACAO = 6000_000;
	
	//public static final String TOKEN_SENHA = "17d197dd-654a-4f2d-98f2-a016bf079793";
	
//	@Value("${jwt.secret}")
//	private String secret;
//	
//	@Value("${jwt.expiration}")
//	private String expiration;
	
	public String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e){
			username = null;
		}
		return username;
	}
	
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e){
			expiration = null;
		}
		return expiration;
	}
	
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey("nonouse")
					.parseClaimsJws(token)
					.getBody();
		}catch (Exception e) {
			claims = null;
		}
		return claims;
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		
		claims.put(TAREFA_USERNAME, userDetails.getUsername());
		
		final Date createdDate = new Date();
		claims.put(TAREFA_CREATED, createdDate);
		
		return doGenerateToken(claims);
		
	}
	
	private String doGenerateToken(Map<String,Object> claims) {
		final Date createdDate = (Date) claims.get(TAREFA_CREATED);
		final Date expirationDate = new Date(createdDate.getTime() + 600);
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512,"nounouse")
				.compact();
	}
	
	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token));
	}
	
	public String refreshToken(String token) {
		String refreshedToken;
		try {
			final Claims claims = getClaimsFromToken(token);
			claims.put(TAREFA_CREATED, new Date());
			refreshedToken = doGenerateToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
		
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		JwtUser user = (JwtUser) userDetails;
		final String username = getUsernameFromToken(token);
		return(
				username.equals(user.getUsername()) && !isTokenExpired(token));
	}

}
