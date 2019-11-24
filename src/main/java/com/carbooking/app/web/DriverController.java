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
import com.carbooking.app.model.DriverEntity;
import com.carbooking.app.service.DriverService;

@RestController
@RequestMapping("/drivers")
public class DriverController {
	
	@Autowired
	DriverService service;

	@GetMapping
	public ResponseEntity<List<DriverEntity>> getAllDrivers() {
		List<DriverEntity> list = service.getAllDrivers();
		return new ResponseEntity<List<DriverEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DriverEntity> getDriverById(@PathVariable("id") Long id) throws RecordNotFoundException {
		DriverEntity entity = service.getDriverById(id);
		return new ResponseEntity<DriverEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<DriverEntity> createOrUpdateDriver(@RequestBody DriverEntity driver) throws RecordNotFoundException {
		DriverEntity updated = service.createOrUpdateDriverInfo(driver);
		return new ResponseEntity<DriverEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteDriverById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteDriverById(id);
		return HttpStatus.FORBIDDEN;
	}

}