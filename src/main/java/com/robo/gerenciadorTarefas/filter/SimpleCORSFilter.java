package com.robo.gerenciadorTarefas.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;

//import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCORSFilter implements Filter{
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Override
	public void init(FilterConfig fc) throws ServletException{
		logger.info("Tarefas - API | SimpleCORSFilter loaded");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpServletRequest request = (HttpServletRequest) req;
//		response.setHeader("Access-Control-Allow-Origin","*");
//		response.setHeader("Access-Control-Allow-Methods","POST, GET, OPTIONS, DELETE, PUT");
//		response.setHeader("Access-Control-Max-Age","3600");
//		response.setHeader("Access-Control-Allow-Headers","x-requested-with, authorization, Content-Type, Authorization");
//		
		if("OPTONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			chain.doFilter(req, resp);
		}
		
		
	}
	
	
	protected void configure(HttpSecurity http) throws Exception{
    	http.httpBasic();
		http.cors();
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/").permitAll()
		.antMatchers(HttpMethod.GET, "/usuario**/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/usuario**/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
			.formLogin()
			.permitAll()
		.and()
			.logout()
			.permitAll();
	}
	
	@Override
	public void destroy() {
		
	}

}
