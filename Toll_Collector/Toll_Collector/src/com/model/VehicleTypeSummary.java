package com.model;

public class VehicleTypeSummary {

	private String vehicleType;
	private Integer vehiclePriceCollection;
	private Integer vehiclePassedCount=0;
	
	

	public VehicleTypeSummary() {
		super();
	}

	public VehicleTypeSummary(String vehicleType, Integer vehiclePriceCollection, Integer vehiclePassedCount) {
		super();
		this.vehicleType = vehicleType;
		this.vehiclePriceCollection = vehiclePriceCollection;
		this.vehiclePassedCount = vehiclePassedCount;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	public Integer getVehiclePriceCollection() {
		return vehiclePriceCollection;
	}

	public void setVehiclePriceCollection(Integer vehiclePriceCollection) {
		this.vehiclePriceCollection = vehiclePriceCollection;
	}

	public Integer getVehiclePassedCount() {
		return vehiclePassedCount;
	}

	public void setVehiclePassedCount(Integer vehiclePassedCount) {
		this.vehiclePassedCount = vehiclePassedCount;
	}
	
	
}
