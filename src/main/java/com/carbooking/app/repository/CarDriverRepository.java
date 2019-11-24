package com.carbooking.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carbooking.app.model.CarDriverEntity;

@Repository
public interface CarDriverRepository extends JpaRepository<CarDriverEntity, Long> {

	@Query(value = "SELECT * FROM TBL_CARS_DRIVERS u WHERE u.car_id = :car_id", nativeQuery = true)
	List<CarDriverEntity> findDriverDetailsByCarId(String car_id);

	@Query(value = "SELECT * FROM TBL_CARS_DRIVERS u WHERE u.driver_id = :driver_id", nativeQuery = true)
	List<CarDriverEntity> findCarDetailsByDriverId(Long driver_id);

}
