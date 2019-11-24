package com.carbooking.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carbooking.app.exception.RecordNotFoundException;
import com.carbooking.app.model.CarEntity;
import com.carbooking.app.repository.CarRepository;

@Service
public class CarService {

	@Autowired
	CarRepository repository;

	public List<CarEntity> getAllCars() {
		List<CarEntity> carList = repository.findAll();
		if (carList.size() > 0) {
			return carList;
		} else {
			return new ArrayList<CarEntity>();
		}
	}

	public CarEntity getCarById(Long id) throws RecordNotFoundException {
		Optional<CarEntity> carEntity = repository.findById(id);
		if (carEntity.isPresent()) {
			return carEntity.get();
		} else {
			throw new RecordNotFoundException("No record exist for given id");
		}
	}

	public CarEntity createOrUpdateCarInfo(CarEntity entity) throws RecordNotFoundException {
		Optional<CarEntity> carEntity = repository.findById(entity.getId());
		if (carEntity.isPresent()) {
			CarEntity newEntity = carEntity.get();
			newEntity.setCarNumber(entity.getCarNumber());
			newEntity.setCarModel(entity.getCarModel());
			newEntity = repository.save(newEntity);
			return newEntity;
		} else {
			entity = repository.save(entity);
			return entity;
		}
	}

	public void deleteCarById(Long id) throws RecordNotFoundException {
		Optional<CarEntity> carEntity = repository.findById(id);
		if (carEntity.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No record exist for given id");
		}
	}
	
}