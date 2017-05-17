package com.byl.enjoylottery.model;

import java.util.List;

public class CityModel {
	private String name;
	private String code;
	private List<DistrictModel> districtList;
	
	public CityModel() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public CityModel(String name, String code, List<DistrictModel> districtList) {
		super();
		this.name = name;
		this.code = code;

		this.districtList = districtList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DistrictModel> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<DistrictModel> districtList) {
		this.districtList = districtList;
	}

	@Override
	public String toString() {
		return "CityModel [name=" + name + ", districtList=" + districtList
				+ "]";
	}
	
}
