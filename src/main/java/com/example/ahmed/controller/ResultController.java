package com.example.ahmed.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.ahmed.model.ResultApi;
import com.example.ahmed.model.ResultRepository;

import ch.qos.logback.classic.Logger;
import net.bytebuddy.asm.Advice.Origin;


@RestController
@RequestMapping("/api2/")
public class ResultController {

    
@Autowired
RestTemplate restTemplate;  
@Autowired
ResultRepository resultRepository;

    @GetMapping("call/{number}")
    public  ResponseEntity<ResultApi> validate(@PathVariable String number ) {
    	String access_key ="56737d1f454e078e94412cd326d8147a";
    	Map<String, String> vars = new HashMap<>();
    	vars.put("access_key",access_key );
    	vars.put("number", number);
    	System.out.println("iam here");
    	String url = "http://apilayer.net/api/validate?access_key={access_key}&number={number}";
    	 
    	ResponseEntity<ResultApi>  responseEntity=restTemplate.getForEntity(url, ResultApi.class,vars);
    	 System.out.println( responseEntity);
    	 return  responseEntity;
    }
    
    @CrossOrigin
	@PostMapping("save")
	public ResultApi saveResult (@RequestBody ResultApi result) {
		
		return  resultRepository.save(result);
	}
    @CrossOrigin
	@GetMapping ("getAll")
	List<ResultApi> getAll(){
		return  resultRepository.findAll();
	}
	
}
