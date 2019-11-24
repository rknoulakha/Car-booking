package com.carbooking.app.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carbooking.app.exception.RecordNotFoundException;
import com.carbooking.app.model.CarEntity;
import com.carbooking.app.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {
	@Autowired
	CarService service;

	@GetMapping
	public ResponseEntity<List<CarEntity>> getAllCars() {
		List<CarEntity> list = service.getAllCars();
		return new ResponseEntity<List<CarEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CarEntity> getCarById(@PathVariable("id") Long id) throws RecordNotFoundException {
		CarEntity entity = service.getCarById(id);
		return new ResponseEntity<CarEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CarEntity> createOrUpdateCarsDetails(@RequestBody CarEntity cars)
			throws RecordNotFoundException {
		CarEntity updated = service.createOrUpdateCarInfo(cars);
		return new ResponseEntity<CarEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteCarById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteCarById(id);
		return HttpStatus.FORBIDDEN;
	}

}