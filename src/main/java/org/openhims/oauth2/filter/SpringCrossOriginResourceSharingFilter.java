package org.openhims.oauth2.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;




@Component
public class SpringCrossOriginResourceSharingFilter extends BaseCORSFilter implements Filter
{
	@Value("${cors.allowed.origins}")
    String allowedOriginsString;
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException 
	{
		 if(request instanceof HttpServletRequest) {
	            if (((HttpServletRequest) request).getHeader("Origin") != null) {
	                String origin = ((HttpServletRequest) request).getHeader("Origin");
	                if (getAllowedOrigins(allowedOriginsString).contains(origin)) {
	                    HttpServletResponse httpServletResponse = (HttpServletResponse) request;
	                    httpServletResponse.setHeader("Access-Control-Allow-Origin", origin);
	                    httpServletResponse.setHeader("Access-Control-Allow-Methods",  "GET, POST, PUT, DELETE, OPTIONS");
	                    httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
	                    httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with, Authorization, Content-Type");
	                }
	            }
	        }
	        filterChain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException 
	{
		
	}

}
