package com.example.ahmed.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DtoRepository extends JpaRepository<SimpleDto, Long> {
 
	 public List<SimpleDto> findByname(String name) ;
		 
		 
	 
	
}
