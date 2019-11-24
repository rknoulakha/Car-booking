package com.carbooking.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_DRIVERS")
public class DriverEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "driver_name")
	private String driverName;

	@Column(name = "driver_mob")
	private String driverMob;

	@Column(name = "driver_email")
	private String driverEmail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverMob() {
		return driverMob;
	}

	public void setDriverMob(String driverMob) {
		this.driverMob = driverMob;
	}

	public String getDriverEmail() {
		return driverEmail;
	}

	public void setDriverEmail(String driverEmail) {
		this.driverEmail = driverEmail;
	}

	@Override
	public String toString() {
		return "DriverEntity [id=" + id + ", driverName=" + driverName + ", driverMob=" + driverMob + ", driverEmail="
				+ driverEmail + "]";
	}

}
