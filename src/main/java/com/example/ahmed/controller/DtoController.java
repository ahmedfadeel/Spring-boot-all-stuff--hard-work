package com.example.ahmed.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.ahmed.exception.ResourceNotFoundException;
import com.example.ahmed.model.DtoRepository;
import com.example.ahmed.model.SimpleDto;

@RestController
@CrossOrigin("*")
@RequestMapping("/dtos")
public class DtoController {
  /*   pagination
   * 
   * 
   * 
   * 
   * */
	 @Autowired
	 DtoRepository dtoRepository;
	 
	 Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	 
	 @PostMapping("/save")
	 public SimpleDto save (@Valid @RequestBody SimpleDto simpleDto) {
		 return dtoRepository.save(simpleDto);
	 }
	 
	 @GetMapping("")
	 public ResponseEntity<?>getAllDtos() {
          
		  
		 List<SimpleDto>  simpleDtos=dtoRepository.findAll();
		
		 if (simpleDtos.isEmpty() ) {
	      throw new  ResourceNotFoundException("no dtos found ");
		 }
		 return  ResponseEntity.ok(simpleDtos);
	 }
	 @GetMapping("/name")
	 public ResponseEntity<?> getDtobyName(@RequestParam (required = true) String name ){
		 /*name value dos not exists so list is empty 
		     name value is null 
		  * */
		 logger.info(name);
		
			
		       
		 List <SimpleDto>simpleDtos=dtoRepository.findByname(name);
		 
		 return  ResponseEntity.ok(simpleDtos);
		 
	 }
	 
  @GetMapping("ex")
  public ResponseEntity<?> throe() {
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)                 
            .body("");
  }
}
