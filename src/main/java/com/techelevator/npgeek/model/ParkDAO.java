package com.techelevator.npgeek.model;

import java.util.List;

public interface ParkDAO {
	
	public List<Park> getAllParks();
	
	public Park getParkByParkCode(String parkCode);
	
	

}
