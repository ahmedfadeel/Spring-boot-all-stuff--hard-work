package com.example.ahmed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
/* refresh token work 
 *  
 * what  do you want ?
 * test api  EmployeeRest  
 * request param   
 *  - value does not exists 
 *  - request param is wrong 
 *  
 * 
 * 1 -  make pagination repository for dots 
 * 2 -  
 * 
 * 
 *
 * */

import com.example.ahmed.controller.EmployeeController;
import com.example.ahmed.exception.ResourceNotFoundException;
  

@SpringBootApplication
public class SpringbootBackendApplication {
	 Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	@Bean
	  public RestTemplate  getRestTemplate() {
		
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
		
	}

}
