package com.example.demo.inter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
@WebFilter(urlPatterns = "/*", filterName = "webContextFilter")
public class WebContextFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletResponse rsp = (HttpServletResponse) response;
		rsp.setHeader("Access-Control-Allow-Origin", "*");
		rsp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		rsp.setHeader("Access-Control-Max-Age", "3600");
		rsp.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		chain.doFilter(request, rsp);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}