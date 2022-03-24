package com.robo.gerenciadorTarefas.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String authoToken = request.getHeader("Authorization");
		String username = jwtTokenUtil.getUsernameFromToken(authoToken);
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if(jwtTokenUtil.validateToken(authoToken, userDetails)) {
				UsernamePasswordAuthenticationToken authencation = new UsernamePasswordAuthenticationToken(
						userDetails,null,userDetails.getAuthorities());
				authencation.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				logger.info("Usuaŕio autenticado" + username + ", configurando contexto de segurança");
				SecurityContextHolder.getContext().setAuthentication(authencation);
				
			}
		}
		chain.doFilter(request, response);
	}
	

}
