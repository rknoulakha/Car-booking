package com.carbooking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carbooking.app.model.DriverEntity;

public interface DriverRepository extends JpaRepository<DriverEntity, Long> {

}
