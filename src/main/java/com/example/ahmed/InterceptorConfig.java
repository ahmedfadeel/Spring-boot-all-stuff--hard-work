package com.example.ahmed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class InterceptorConfig  extends WebMvcConfigurerAdapter{
  @Autowired
  PreRequestInterceptor requestInterceptror;

@Override
public void addInterceptors(InterceptorRegistry  registry) {
	// TODO Auto-generated method stub
	registry.addInterceptor(requestInterceptror);
}
  
  
}
