package com.model;

public class VehicleData {

	
	private Integer fastTagAmount=0;
	private Boolean hasFastTag=false;
	private Integer countRound=0;
	private String vehicleCategory;
	
	public VehicleData() {
		super();
	}

	public VehicleData(Integer fastTagAmount, Boolean hasFastTag, Integer countRound, String vehicleCategory) {
		super();
		this.fastTagAmount = fastTagAmount;
		this.hasFastTag = hasFastTag;
		this.countRound = countRound;
		this.vehicleCategory = vehicleCategory;
	}

	public Integer getFastTagAmount() {
		return fastTagAmount;
	}

	public void setFastTagAmount(Integer fastTagAmount) {
		this.fastTagAmount = fastTagAmount;
	}

	public Boolean getHasFastTag() {
		return hasFastTag;
	}

	public void setHasFastTag(Boolean hasFastTag) {
		this.hasFastTag = hasFastTag;
	}

	public Integer getCountRound() {
		return countRound;
	}

	public void setCountRound(Integer countRound) {
		this.countRound = countRound;
	}

	public String getVehicleCategory() {
		return vehicleCategory;
	}

	public void setVehicleCategory(String vehicleCategory) {
		this.vehicleCategory = vehicleCategory;
	}

	
	
	
	
}
