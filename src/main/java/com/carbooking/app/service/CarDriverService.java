package com.carbooking.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carbooking.app.model.CarDriverEntity;
import com.carbooking.app.model.CarDriverInfo;
import com.carbooking.app.model.CarEntity;
import com.carbooking.app.model.DriverEntity;
import com.carbooking.app.repository.CarDriverRepository;

@Service
public class CarDriverService {

	@Autowired
	CarDriverRepository repository;

	@Autowired
	CarService carService;

	@Autowired
	DriverService driverService;

	public CarDriverEntity createCarDriversInfo(CarDriverEntity entity) throws Exception {
		List<CarDriverEntity> carEntity = null;
		  carEntity = repository.findDriverDetailsByCarId(entity.getCarId()); 
		  if (null != carEntity && carEntity.size()>0) 
		  { throw new Exception("Car is already associated with driver: ");
		  } else {
		carEntity = repository.findCarDetailsByDriverId(entity.getDriverId());
		if (null != carEntity && carEntity.size() > 0) {
			throw new Exception("Driver is already associated with car: ");
		}
		}
		entity = repository.save(entity);
		return entity;
	}

	List<CarDriverEntity> findAll() {
		return repository.findAll();
	}

	public List<CarDriverInfo> driversWithCarDetails() throws Exception {
		List<CarDriverEntity> carDriverEntity = findAll();
		CarDriverInfo carDriverInfo = null;
		List<CarDriverInfo> carDriverInfoList = new ArrayList<>();
		for (CarDriverEntity carDrvEntity : carDriverEntity) {
			carDriverInfo = new CarDriverInfo();
			CarEntity carEntity = carService.getCarById(carDrvEntity.getCarId());
			DriverEntity driverEntity = driverService.getDriverById(carDrvEntity.getDriverId());
			carDriverInfo.setCarId(carEntity.getId());
			carDriverInfo.setCarModel(carEntity.getCarModel());
			carDriverInfo.setCarNumber(carEntity.getCarNumber());
			carDriverInfo.setDriverId(driverEntity.getId());
			carDriverInfo.setDriverName(driverEntity.getDriverName());
			carDriverInfo.setDriverMob(driverEntity.getDriverMob());
			carDriverInfo.setDriverEmail(driverEntity.getDriverEmail());
			carDriverInfoList.add(carDriverInfo);
		}
		return carDriverInfoList;
	}

	public List<Long> availablrDriver() {
		List<CarDriverEntity> carDriverEntity = findAll();
		List<Long> busyDriverId = new ArrayList();
		List<Long> totalDriverId = new ArrayList();

		for (CarDriverEntity carDriverEnt : carDriverEntity) {
			busyDriverId.add(carDriverEnt.getDriverId());
		}

		List<DriverEntity> driverEntity = driverService.getAllDrivers();
		for (DriverEntity driverEnt : driverEntity) {
			totalDriverId.add(driverEnt.getId());
		}
		totalDriverId.removeAll(busyDriverId);
		return totalDriverId;
	}
}