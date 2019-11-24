package com.carbooking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carbooking.app.model.CarEntity;
 
@Repository
public interface CarRepository
        extends JpaRepository<CarEntity, Long> {
 
}
