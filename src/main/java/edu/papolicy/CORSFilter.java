package edu.papolicy;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class CORSFilter implements Filter {
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Authorization, content-type");
		response.setHeader("Access-Control-Max-Age", "3600");
		chain.doFilter(req, res);
	}
	public void init(FilterConfig filterConfig) {}
	public void destroy() {}
}
