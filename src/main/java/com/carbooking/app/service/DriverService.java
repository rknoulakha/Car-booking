package com.carbooking.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carbooking.app.exception.RecordNotFoundException;
import com.carbooking.app.model.DriverEntity;
import com.carbooking.app.repository.DriverRepository;

@Service
public class DriverService {

	@Autowired
	DriverRepository repository;

	public List<DriverEntity> getAllDrivers() {
		List<DriverEntity> driverList = repository.findAll();
		if (driverList.size() > 0) {
			return driverList;
		} else {
			return new ArrayList<DriverEntity>();
		}
	}

	public DriverEntity getDriverById(Long id) throws RecordNotFoundException {
		Optional<DriverEntity> driver = repository.findById(id);
		if (driver.isPresent()) {
			return driver.get();
		} else {
			throw new RecordNotFoundException("No record exist for given id");
		}
	}

	public DriverEntity createOrUpdateDriverInfo(DriverEntity entity) throws RecordNotFoundException {
		Optional<DriverEntity> driver = repository.findById(entity.getId());
		if (driver.isPresent()) {
			DriverEntity newEntity = driver.get();
			newEntity.setId(entity.getId());
			newEntity.setDriverName(entity.getDriverName());
			newEntity.setDriverMob(entity.getDriverMob());
			newEntity.setDriverEmail(entity.getDriverEmail());
			newEntity = repository.save(newEntity);
			return newEntity;
		} else {
			entity = repository.save(entity);
			return entity;
		}
	}

	public void deleteDriverById(Long id) throws RecordNotFoundException {
		Optional<DriverEntity> driver = repository.findById(id);
		if (driver.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No record exist for given id");
		}
	}
}