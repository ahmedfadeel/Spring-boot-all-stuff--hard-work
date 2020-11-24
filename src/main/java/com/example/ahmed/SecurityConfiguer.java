package com.example.ahmed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.ahmed.service.MyUserDetailsService;
@EnableWebSecurity
@Configuration
public class SecurityConfiguer  extends WebSecurityConfigurerAdapter{
	@Autowired
	MyUserDetailsService userDetailsService;
	@Autowired 
	JwtFilter jwtFilter;

	private static final String[] PUBLIC_MATCHES= {
			 "/users/register",
			 "/api2/**",			
			 "/dtos/**"		 
	};
	
	/*
	
      ...
    .antMatchers("/auth/admin/*").hasRole("ADMIN")
    .antMatchers("/auth/*").hasAnyRole("ADMIN","USER")
    ...
 
    i need to implement referesh token  not to every time ask my user to authenticate 
    w
 
 
 
	  */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.cors().and()
		 .csrf().disable().exceptionHandling().and().sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		 .authorizeRequests().antMatchers("/authenticate")
         .permitAll()
         .antMatchers(PUBLIC_MATCHES).permitAll()
         .anyRequest().authenticated();
         
 http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);;
	   
	}
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
}
	