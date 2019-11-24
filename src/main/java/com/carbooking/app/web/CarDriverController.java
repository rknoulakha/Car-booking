package com.carbooking.app.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carbooking.app.model.CarDriverEntity;
import com.carbooking.app.model.CarDriverInfo;
import com.carbooking.app.service.CarDriverService;

@RestController
@RequestMapping("/car-driver")
public class CarDriverController {

	@Autowired
	CarDriverService service;

	@PostMapping
	public ResponseEntity<CarDriverEntity> createCarDriversInfo(@RequestBody CarDriverEntity cars) throws Exception {
		CarDriverEntity updated = service.createCarDriversInfo(cars);
		return new ResponseEntity<CarDriverEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/car-details")
	public ResponseEntity<List<CarDriverInfo>> getAllCars() throws Exception {
		List<CarDriverInfo> list = service.driversWithCarDetails();
		return new ResponseEntity<List<CarDriverInfo>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/avail-drivers")
	public ResponseEntity<List<Long>> availablrDriver() throws Exception {
		List<Long> list = service.availablrDriver();
		return new ResponseEntity<List<Long>>(list, new HttpHeaders(), HttpStatus.OK);
	}

}