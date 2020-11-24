package com.example.ahmed;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;
import com.example.ahmed.model.MyUserDetails;
import com.example.ahmed.service.MyUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
@Component
public class JwtFilter extends OncePerRequestFilter {
   @Autowired
   private MyUserDetailsService userDetails_Service;
   @Autowired	 
   private JwtUtil jwtUtil;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
		String authorizationHeader=request.getHeader("Authorization");
		
		String token =null;
		String userName=null;
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
			token=authorizationHeader.substring(7);
			userName=jwtUtil.extractUsername(token);
			/*get token from authorization header 
			 * get userName from token 
			 * validate user by userName and token 
			 * 
			 * 
			 * 
			 * */
		}
	   if (userName!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
		    MyUserDetails myUserDetails=userDetails_Service.loadUserByUsername(userName);
		    if (jwtUtil.validateToken(token, myUserDetails)) {
		    	
		    	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(myUserDetails, null, myUserDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		    }
		   
	   }	
		}
			
			catch (ExpiredJwtException ex) {

				String isRefreshToken = request.getHeader("isRefreshToken");
				String requestURL = request.getRequestURL().toString();
				// allow for Refresh Token creation if following conditions are true.
				if (isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("refreshtoken")) {
					allowForRefreshToken(ex, request);
				} else
					request.setAttribute("exception", ex);

			} 
		
	   filterChain.doFilter(request, response);
	}
 
	private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {

		// create a UsernamePasswordAuthenticationToken with null values.
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				null, null, null);
		// After setting the Authentication in the context, we specify
		// that the current user is authenticated. So it passes the
		// Spring Security Configurations successfully.
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		// Set the claims so that in controller we will be using it to create
		// new JWT
		request.setAttribute("claims", ex.getClaims());

	}

	private String extractJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

}


