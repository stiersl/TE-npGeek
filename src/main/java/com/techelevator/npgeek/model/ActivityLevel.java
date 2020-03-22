package com.techelevator.npgeek.model;

public enum ActivityLevel {

	
	INACTIVE("Inactive"), SEDENTARY("Sedentary"), ACTIVE("Active"),EXTREMELY_ACTIVE("Extremely Active");
	
	private String name;
	
	private ActivityLevel(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
