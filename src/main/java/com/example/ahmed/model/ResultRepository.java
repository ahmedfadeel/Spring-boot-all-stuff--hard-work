package com.example.ahmed.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ResultRepository extends JpaRepository<ResultApi, Long> {

}
