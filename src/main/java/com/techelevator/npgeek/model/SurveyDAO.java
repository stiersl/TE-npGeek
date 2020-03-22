package com.techelevator.npgeek.model;

import java.util.List;

public interface SurveyDAO {
	
	public List<SurveyResult> getAllSurveys();
	
	public boolean createSurvey(SurveyResult survey);
	
	public List<Park> getParksOrderedBySurveys();

}
